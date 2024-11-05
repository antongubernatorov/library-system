package ru.gubern.library.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import ru.gubern.library.dao.PersonDao;
import ru.gubern.library.models.Person;
import ru.gubern.library.service.PersonService;
import ru.gubern.library.utils.PersonValidator;

@Controller
@RequestMapping("")
public class PeopleController {

    private final PersonService personService;
    private final PersonValidator validator;

    @Autowired
    public PeopleController(PersonService personService, PersonValidator validator) {
        this.personService = personService;
        this.validator = validator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personService.findAll());
        return "people/index";
    }

    @GetMapping("{/id")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.findOne(id));
        model.addAttribute("books", personService.getBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        validator.validate(person, bindingResult);

        if (bindingResult.hasErrors()){
            return "/people/new";
        }

        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model,  @PathVariable("id") int id){
        model.addAttribute("person", personService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "people/edit";
        }

        personService.update(id, person);
        return "people/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personService.delete(id);
        return "redirect:people";
    }

}
