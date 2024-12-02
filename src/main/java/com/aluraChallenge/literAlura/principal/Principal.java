package com.aluraChallenge.literAlura.principal;

import com.aluraChallenge.literAlura.controller.AuthorController;
import com.aluraChallenge.literAlura.controller.BookController;
import com.aluraChallenge.literAlura.view.View;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Principal class represents the main application controller for LiterAlura.
 */
public class Principal {
    private final Scanner keyboard = new Scanner(System.in);
    private final AuthorController authorController;
    private final BookController bookController;

    /**
     * Constructs a Principal object with the specified AuthorController and BookController.
     *
     * @param authorController the AuthorController object
     * @param bookController the BookController object
     */
    public Principal(AuthorController authorController, BookController bookController) {
        this.authorController = authorController;
        this.bookController = bookController;
    }

    /**
     * Displays a welcome message to the user.
     */
    public void welcome() {
        System.out.println("\n*** Welcome to LiterAlura ***");
    }

    /**
     * Displays the main menu and handles user input for various options.
     */
    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    -------------------------------------
                    1 - Search book by title
                    2 - List registered books
                    3 - List registered authors
                    4 - List living authors since a specific year
                    5 - List books by language
                    6 - Search author by name
                    7 - Top 10 most downloaded books
                    0 - Exit""";

            try {
                System.out.println(menu);
                option = keyboard.nextInt();
                keyboard.nextLine();

                switch (option) {
                    case 1:
                        bookController.searchBookByTitle(View.searchBookByTitle());
                        break;
                    case 2:
                        View.showBooks(bookController.getAllRegisteredBooks());
                        break;
                    case 3:
                        View.showAuthors(authorController.getAllRegisteredAuthors());
                        break;
                    case 4:
                        View.showAuthors(authorController.getLivingAuthorsByYear(View.getValidYear()));
                        break;
                    case 5:
                        View.showBooks(bookController.getBooksByLanguages(View.getLanguages()));
                        break;
                    case 6:
                        bookController.searchAuthorByName(View.searchAuthorByName());
                        break;
                    case 7:
                        View.showBooks(bookController.getTop10MostDownloaded());
                        break;
                    case 0:
                        System.out.println("Closing the application...");
                        System.out.println("  *** Thank you for using Literatura ***");
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option");
                keyboard.next();
            }
        }
    }
}