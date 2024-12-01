package com.aluraChallenge.literAlura.service;

import com.aluraChallenge.literAlura.entity.Book;
import com.aluraChallenge.literAlura.model.Languages;
import com.aluraChallenge.literAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for book-related operations.
 */
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    BookRepository bookRepository;

    /**
     * Retrieves all registered books sorted by title.
     *
     * @return A list of books sorted by title.
     */
    @Override
    public List<Book> getAllRegisteredBooks() {
        List<Book> bookList = bookRepository.findAll();

        return bookList.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new book to the repository.
     *
     * @param book The book to be added.
     */
    @Override
    public void addNewBook(Book book) {
        try {
            bookRepository.save(book);
            System.out.println("Your book was add successfully.");
        } catch (Exception ex) {
            System.out.println("Error adding the book.");
        }
    }

    /**
     * Finds books by the specified language.
     * This method overrides the base implementation and delegates the search to the repository layer.
     * @param languageSelected the language to search books by
     * @return a list of books that are written in the specified language
     */
    @Override
    public List<Book> findByLanguages(Languages languageSelected) {
        return bookRepository.findByLanguages(languageSelected);
    }
}