package ru.gubern.library.dao;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonDao {

    public Optional<Object> getPersonByFullName(String firstName, String middleName, String lastName) {
        return null;
    }
}
