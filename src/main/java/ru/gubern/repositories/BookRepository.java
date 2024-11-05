package ru.gubern.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gubern.library.models.Book;
import ru.gubern.library.models.Person;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
