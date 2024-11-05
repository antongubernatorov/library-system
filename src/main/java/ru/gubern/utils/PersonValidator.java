package ru.gubern.library.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.gubern.library.dao.PersonDao;
import ru.gubern.library.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors e) {
        Person person = (Person) o;

        if (personDao.getPersonByFullName(person.getFirstName(), person.getMiddleName(),person.getLastName()).isPresent()){
            e.rejectValue("fullName", "", "Person with this FML already exists");
        }
    }
}
