package com.aluraChallenge.literAlura.view;

import com.aluraChallenge.literAlura.entity.Author;
import com.aluraChallenge.literAlura.entity.Book;
import com.aluraChallenge.literAlura.model.Languages;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents a class for handling views in the application.
 */
public class View {
    private static final Scanner keyboard = new Scanner(System.in);

    /**
     * Displays books.
     *
     * @param bookList The list of books to display.
     */
    public static void showBooks(List<Book> bookList) {
        if (bookList.isEmpty()) {
            System.out.println("No books found.");
        } else {
            bookList.forEach(b -> {
                System.out.println("*******************************");
                System.out.printf("Book: %s |\nAuthor: %s |\nLanguage: %s | Download: %.1f \n",
                        b.getTitle(), b.getAuthor(), b.getLanguages(), b.getNumberOfDownloads());
            });
        }
    }

    /**
     * Displays authors.
     *
     * @param authorList The list of authors to display.
     */
    public static void showAuthors(List<Author> authorList) {
        if (authorList.isEmpty()) {
            System.out.println("No authors found");
        } else {
            authorList.forEach(
                    a -> {
                        String booksTitles = a.getBookList().stream()
                                .map(Book::getTitle)
                                .collect(Collectors.joining(" / "));

                        System.out.printf("Author: %s | %d - %d | Books: %s \n", a.getName(),
                                a.getYearOfBirth(), a.getYearOfDeath(), booksTitles);
                    });
        }

    }

    /**
     * Prompts the user to enter the title of the book to search for.
     *
     * @return The title of the book to search for.
     */
    public static String searchBookByTitle() {
        System.out.println("Please enter the title of the book you want to search for:");
        return keyboard.nextLine();
    }

    /**
     * Prompts the user to enter a valid year for searching living authors.
     * The user cannot exit the loop until a valid year is entered.
     *
     * @return the valid year entered by the user
     */
    public static int getValidYear() {
        int date = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Please enter the year for searching living authors:");
                date = keyboard.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                keyboard.next();
            }
        }
        return date;
    }

    /**
     * Method to get the language selected by the user.
     * It displays the options,
     * gets the input, and validates it.
     *
     * @return the validated language input as a String
     */
    public static String getLanguages() {
        displayLanguageOptions();

        String input;
        while (true) {
            input = getUserInput();
            if (isValidStringInput(input)) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid language name without numbers or whitespace.");
        }

        return input;
    }

    /**
     * Displays the available language options to the user.
     */
    public static void displayLanguageOptions() {
        System.out.println("Select the language:");
        for (Languages language : Languages.values()) {
            System.out.println("- " + language.name());
        }
    }

    /**
     * Gets the user input from the console.
     *
     * @return the user input as a String
     */
    public static String getUserInput() {
        return keyboard.nextLine();
    }

    /**
     * Validates the user input to ensure it does not contain any digits.
     *
     * @param input the user input
     * @return true if the input is valid, false otherwise
     */
    public static boolean isValidStringInput(String input) {
        return !input.matches(".*\\d.*") && !input.trim().isEmpty();
    }

    /**
     * Prompts the user to enter the name of the author to search for.
     *
     * @return The author of the book to search for.
     */
    public static String searchAuthorByName() {
        String input;
        while (true) {
            System.out.println("Please enter the name of the author you want to search for:");
            input = getUserInput();
            if (!isValidStringInput(input)) {
                System.out.println("Invalid input. Please enter a valid name without numbers or whitespace.");
            }
            else if (input.length() < 3) {
                System.out.println("The name must have at least 3 letters.");
            } else {
                break;
            }
        }
        return input;
    }
}