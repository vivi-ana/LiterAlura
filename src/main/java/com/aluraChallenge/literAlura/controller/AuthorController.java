package com.aluraChallenge.literAlura.controller;

import com.aluraChallenge.literAlura.entity.Author;
import com.aluraChallenge.literAlura.service.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * AuthorController class serves as the controller for managing Author-related operations.
 */
@Controller
public class AuthorController {
    @Autowired
    AuthorServiceImpl authorService;

    /**
     * Retrieves a list of all registered authors.
     *
     * @return a list of Author objects representing all registered authors
     */
    public List<Author> getAllRegisteredAuthors() {
        return authorService.getAllRegisteredAuthors();
    }

    /**
     * Retrieves a list of living authors who were born in or after a specific year.
     *
     * @param year the year to filter living authors
     * @return a list of Author objects representing living authors based on the provided year
     */
    public List<Author> getLivingAuthorsByYear(int year) {
        return authorService.getLivingAuthorsByYear(year);
    }
}