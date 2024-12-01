package com.aluraChallenge.literAlura.entity;

import com.aluraChallenge.literAlura.model.BookData;
import com.aluraChallenge.literAlura.model.Languages;
import jakarta.persistence.*;

import java.util.Optional;

/**
 * Book entity represents a book written by an author.
 */
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    private Languages languages;

    private Double numberOfDownloads;
    @ManyToOne
    @JoinColumn(name = "fk_author", nullable = false)
    private Author author;

    /**
     * Default constructor for Book.
     */
    public Book() {
    }

    /**
     * Constructor for Book that takes BookData as input to initialize the object.
     *
     * @param bookData the BookData object containing book information
     */
    public Book(BookData bookData) {
        this.title = bookData.title();
        this.author = new Author(bookData.authors().get(0));
        Optional<Languages> languageOpt = Languages.fromString(bookData.languages().get(0));
        if (languageOpt.isPresent()) {
            this.languages = languageOpt.get();
        } else {
            throw new IllegalArgumentException("Invalid language: " + bookData.languages().get(0));
        }
        this.numberOfDownloads = bookData.numberOfDownloads();
    }

    public String getTitle() {
        return title;
    }

    public Languages getLanguages() {
        return languages;
    }

    public Double getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return  "\nTitle = '" + title +
                "\nLanguages = " + languages +
                "\nNumber of downloads = " + numberOfDownloads +
                "\nAuthor = " + author;
    }
}