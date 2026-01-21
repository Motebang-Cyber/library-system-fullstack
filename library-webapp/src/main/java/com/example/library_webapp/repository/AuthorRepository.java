package com.example.library_webapp.repository;

import com.example.library_webapp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Author entity.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Future extension:
    // List<Author> findByNameContainingIgnoreCase(String name);
}
