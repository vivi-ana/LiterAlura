package com.aluraChallenge.literAlura.service;

import com.aluraChallenge.literAlura.entity.Book;
import com.aluraChallenge.literAlura.model.Languages;

import java.util.List;

/**
 * Interface for Book Service operations.
 */
public interface IBookService {
    List<Book> getAllRegisteredBooks();
    void addNewBook(Book book);
    List<Book> findByLanguages(Languages languageSelected);
}