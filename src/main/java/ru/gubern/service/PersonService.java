package ru.gubern.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gubern.library.models.Book;
import ru.gubern.library.models.Person;
import ru.gubern.library.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findOne(int id){
        return personRepository.findById(id);
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return personRepository.findAllBooksById(id);
    }
}
