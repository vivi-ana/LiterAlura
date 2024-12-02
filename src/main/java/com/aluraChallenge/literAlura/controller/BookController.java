package com.aluraChallenge.literAlura.controller;

import com.aluraChallenge.literAlura.entity.Author;
import com.aluraChallenge.literAlura.entity.Book;
import com.aluraChallenge.literAlura.model.BookData;
import com.aluraChallenge.literAlura.model.Data;
import com.aluraChallenge.literAlura.model.Languages;
import com.aluraChallenge.literAlura.service.APIService;
import com.aluraChallenge.literAlura.service.AuthorServiceImpl;
import com.aluraChallenge.literAlura.service.BookServiceImpl;
import com.aluraChallenge.literAlura.service.DataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @Autowired
    AuthorServiceImpl authorService;
    private final DataConverter dataConverter = new DataConverter();

    private static final String URL_BASE = "https://gutendex.com/books/";
    private APIService apiService = new APIService();

    /**
     * Searches for a book by title and handles the search logic.
     *
     * @param bookTitle The title of the book to search for.
     */
    public void searchBookByTitle(String bookTitle) {
        try {
            validateInput(bookTitle, "Book title cannot be empty. Please enter a valid title.");

            String json = apiService.fetchData(URL_BASE + "?search=" + bookTitle.replace(" ", "+"));
            Data searchData = dataConverter.getData(json, Data.class);
            Optional<BookData> foundBook = findBookByTitle(searchData, bookTitle);

            if (foundBook.isPresent()) {
                handleFoundBook(foundBook.get());
            } else {
                System.out.println("The book was not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during book search: " + e.getMessage());
        }
    }

    /**
     * Validates a field.
     * @param field The field to validate.
     * @param message The message to show if the validation fails
     * @throws IllegalArgumentException if the field is empty.
     */
    private void validateInput(String field, String message) {
        if (field.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Finds a book by title in the search results.
     *
     * @param searchData The search results containing book data.
     * @param bookTitle The title of the book to search for.
     * @return An optional containing the found book data.
     */
    private Optional<BookData> findBookByTitle(Data searchData, String bookTitle) {
        return searchData.results().stream()
                .filter(b -> b.title().toUpperCase().contains(bookTitle.toUpperCase()))
                .findFirst();
    }

    /**
     * Handles a found book by adding it to the system if it does not already exist.
     *
     * @param foundBook The book data of the found book.
     */
    private void handleFoundBook(BookData foundBook) {
        Book foundBookConverted = new Book(foundBook);
        List<Book> bookList = bookService.getAllRegisteredBooks();
        boolean bookAlreadyExists = bookList.stream()
                .anyMatch(book -> book.getTitle().equalsIgnoreCase(foundBookConverted.getTitle()));

        if (bookAlreadyExists) {
            System.out.println("You have already added this book:");
            System.out.println(foundBookConverted);
        } else {
            processNewBook(foundBookConverted);
        }
    }

    /**
     * Processes a new book by handling author registration and adding the book.
     *
     * @param newBook The new book to be processed.
     */
    private void processNewBook(Book newBook) {
        Author foundAuthor = newBook.getAuthor();
        List<Author> authorList = authorService.getAllRegisteredAuthors();

        Optional<Author> existingAuthor = authorList.stream()
                .filter(author -> author.getName().equalsIgnoreCase(foundAuthor.getName()))
                .findFirst();

        if (existingAuthor.isPresent()) {
            newBook.setAuthor(existingAuthor.get());
        } else {
            authorService.addNewAuthor(foundAuthor);
        }

        bookService.addNewBook(newBook);
        System.out.println("The found book is:");
        System.out.println(newBook);
    }

    /**
     * Retrieves a list of all registered books.
     *
     * @return A list of all books that are registered in the system.
     */
    public List<Book> getAllRegisteredBooks() {
        return bookService.getAllRegisteredBooks();
    }

    /**
     * Retrieves a list of books based on the specified language.
     *
     * @param languages a string representing the language for which books are to be retrieved
     * @return a list of Book objects that belong to the specified language
     */
    public List<Book> getBooksByLanguages(String languages) {
        Optional<Languages> languageSelectedOpt = Languages.fromString(languages);
        if (languageSelectedOpt.isPresent()) {
            Languages languageSelected = languageSelectedOpt.get();
            List<Book> booksByLanguages = bookService.findByLanguages(languageSelected);
            System.out.println("Books in the selected language: " + languageSelected);
            if (!booksByLanguages.isEmpty()) {
                System.out.println("There are " + booksByLanguages.size() + " books.");
            }
            return booksByLanguages;
        } else {
            System.out.println("Invalid language input. The language is not found.");
            return new ArrayList<>();
        }
    }

    /**
     * Searches for authors by name and prints the result.
     * Validates the input to ensure the name is not empty.
     * @param name the name of the author to search for
     */
    public void searchAuthorByName(String name) {
        try {
            validateInput(name, "The author's name cannot be empty. Please enter a valid name.");
            List<Author> authorList = authorService.getAuthorByName(name);
            if (authorList.isEmpty()) {
                System.out.println("No authors found with the name: " + name);
            } else {
                authorList.forEach(author -> System.out.println(author.toString())); }
        } catch (Exception e) {
            System.out.println("An error occurred during author search: " + e.getMessage());
        }
    }

    /**
     * Retrieves the top 10 most downloaded books.
     * This method fetches all registered books, sorts them by the number of downloads
     * in descending order, limits the result to the top 10 books, prints the titles
     * of these books in uppercase, and collects them into a list.
     * @return a list of the top 10 most downloaded books
     */
    public List<Book> getTop10MostDownloaded() {
        List<Book> bookList = bookService.getAllRegisteredBooks();
        return bookList.stream()
                .sorted(Comparator.comparing(Book::getNumberOfDownloads).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}