package ru.gubern.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gubern.library.models.Book;
import ru.gubern.library.models.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Book> findAllBooksById(int id);
}
