package com.example.library_webapp.controller;

import com.example.library_webapp.model.Book;
import com.example.library_webapp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository repo;

    /**
     * PAGINATED + SEARCH + SORT + AUTHOR FILTER
     *
     * Example:
     * /api/books?page=0&size=5&search=java&author=martin&sort=title,asc
     */
    @GetMapping
    public Page<Book> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String author
    ) {

        String[] sortParams = sort.split(",");
        Sort sortObj = Sort.by(
                Sort.Direction.fromString(sortParams[1]),
                sortParams[0]
        );

        Pageable pageable = PageRequest.of(page, size, sortObj);

        if (search != null && author != null) {
            return repo.findByTitleContainingIgnoreCaseAndAuthor_NameContainingIgnoreCase(
                    search, author, pageable);
        }

        if (search != null) {
            return repo.findByTitleContainingIgnoreCase(search, pageable);
        }

        if (author != null) {
            return repo.findByAuthor_NameContainingIgnoreCase(author, pageable);
        }

        return repo.findAll(pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    public Book add(@RequestBody Book book) {
        book.setAvailable(true);
        return repo.save(book);
    }
}
