package com.aluraChallenge.literAlura.entity;

import com.aluraChallenge.literAlura.model.AuthorData;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author entity represents an author of books.
 */
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Integer yearOfBirth;
    private Integer yearOfDeath;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> bookList;

    /**
     * Default constructor for Author. Initializes the bookList as an empty ArrayList.
     */
    public Author() {
        bookList = new ArrayList<>();
    }

    /**
     * Constructor for Author that takes AuthorData as input to initialize the object.
     *
     * @param authorData the AuthorData object containing author information
     */
    public Author(AuthorData authorData ) {
        this.name = authorData.name();
        this.yearOfBirth = authorData.yearOfBirth();
        this.yearOfDeath = authorData.yearOfDeath();
        bookList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public Integer getYearOfDeath() {
        return yearOfDeath;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public String toString() {
        return  "\nName = '" + name +
                "\nYear of birth = " + yearOfBirth +
                "\nYear of death = " + yearOfDeath;
    }
}