package ru.gubern.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should has size between 2 and 100 characters")
    private String firstName;

    @Size(min = 2, max = 100, message = "Middle Name should has size between 2 and 100 characters")
    private String middleName;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should has size between 2 and 100 characters")
    private String lastName;

    @Min(value = 1900, message = "Birthdate date should be not less than 1900")
    private LocalDate birthdate;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

}
