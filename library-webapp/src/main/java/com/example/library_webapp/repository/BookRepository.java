package com.example.library_webapp.repository;

import com.example.library_webapp.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByAuthor_NameContainingIgnoreCase(String author, Pageable pageable);

    Page<Book> findByTitleContainingIgnoreCaseAndAuthor_NameContainingIgnoreCase(
            String title,
            String author,
            Pageable pageable
    );
}
