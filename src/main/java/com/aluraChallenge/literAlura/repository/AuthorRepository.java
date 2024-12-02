package com.aluraChallenge.literAlura.repository;

import com.aluraChallenge.literAlura.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on Author entities.
 * Provides methods to interact with the Author data
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /** * Custom query to find authors who were alive during a specified year.
     * This query selects authors whose year of death is after the specified year
     * and whose year of birth is before the specified year.
     * @param year The year to compare against the year of death and year of birth of authors.
     * @return A list of authors who were alive during the specified year.
     */
    @Query("SELECT a FROM Author a WHERE a.yearOfDeath > :year AND a.yearOfBirth < :year")
    List<Author> findLivingAuthors(int year);

    /**
     * Finds authors whose names contain the provided name pattern, ignoring case.
     * This method uses a SQL LIKE query to search for authors whose names contain
     * the specified pattern, ignoring case differences.
     * @param name the name pattern to search for
     * @return a list of authors matching the name pattern
     */
    @Query("SELECT a FROM Author a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Author> findByName(String name);
}