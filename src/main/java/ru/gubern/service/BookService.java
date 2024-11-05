package ru.gubern.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gubern.library.models.Book;
import ru.gubern.library.models.Person;
import ru.gubern.library.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findOne(int id){
        return bookRepository.findById(id);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updBook){
        updBook.setId(id);
        bookRepository.save(updBook);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return null;
    }

    public void release(int id) {

    }

    public void assign(int id, Person person) {

    }
}
