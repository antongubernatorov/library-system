package ru.gubern.library.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gubern.library.models.Book;
import ru.gubern.library.models.Person;
import ru.gubern.library.service.BookService;
import ru.gubern.library.service.PersonService;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("model", bookService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person")Person person){
        model.addAttribute("book", bookService.findOne(id));

        Optional<Person> bookOwner = bookService.getBookOwner(id);

        if (bookOwner.isPresent()){
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personService.findAll());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
       if (bindingResult.hasErrors()){
           return "books/new";
       }

       bookService.save(book);
       return "books/index";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("{id}/release")
    public String release(@PathVariable("id") int id){
        bookService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookService.assign(id, person);
        return "redirect:/books/" + id;
    }
}
