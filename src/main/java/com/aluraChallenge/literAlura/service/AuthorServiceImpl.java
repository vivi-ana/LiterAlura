package com.aluraChallenge.literAlura.service;

import com.aluraChallenge.literAlura.entity.Author;

import com.aluraChallenge.literAlura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for author-related operations.
 */
@Service
public class AuthorServiceImpl implements IAuthorService {
    @Autowired
    AuthorRepository authorRepository;

    /**
     * Retrieves all registered authors sorted by name.
     *
     * @return A list of authors sorted by name.
     */
    @Override
    public List<Author> getAllRegisteredAuthors() {
        List<Author> authorList = authorRepository.findAll();

        return authorList.stream()
                .sorted(Comparator.comparing(Author::getName))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new author to the repository.
     *
     * @param author The author to be added.
     */
    @Override
    public void addNewAuthor(Author author) {
        try {
            authorRepository.save(author);
        } catch (Exception ex) {
            System.out.println("Error adding the new author.");
        }
    }

    /**
     * Retrieves a list of living authors based on a specific year.
     *
     * @param year The year to consider for finding living authors.
     * @return A list of authors who are considered living in the given year.
     */
    @Override
    public List<Author> getLivingAuthorsByYear(int year) {
        return authorRepository.findLivingAuthors(year);
    }

    /**
     * Retrieves a list of living authors based on a specific year.
     *
     * @param name The year to consider for finding living authors.
     * @return A list of authors who are considered living in the given year.
     */
    @Override
    public List<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }
}