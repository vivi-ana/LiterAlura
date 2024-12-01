package com.aluraChallenge.literAlura.service;

import com.aluraChallenge.literAlura.entity.Author;

import java.util.List;

/**
 * Interface for Author Service operations.
 */
public interface IAuthorService {
    List<Author> getAllRegisteredAuthors();
    void addNewAuthor(Author author);
    List<Author> getLivingAuthorsByYear(int year);
}