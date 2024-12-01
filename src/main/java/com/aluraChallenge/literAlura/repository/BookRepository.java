package com.aluraChallenge.literAlura.repository;

import com.aluraChallenge.literAlura.entity.Book;
import com.aluraChallenge.literAlura.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on Book entities.
 * Provides methods to interact with the Book data.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Custom method to find books by their language.
     * @param languages The language to search books by.
     * @return A list of books that are written in the specified language.
     */
    List<Book> findByLanguages(Languages languages);
}
