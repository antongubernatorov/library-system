package ru.gubern.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 1, max = 128, message = "Book name should be between one and 128 characters")
    private String name;

    @NotEmpty
    private String author;

    private int year;

    @ManyToOne()
    @JoinColumn(name = "reader_id", referencedColumnName = "person_id")
    private Person owner;
}
