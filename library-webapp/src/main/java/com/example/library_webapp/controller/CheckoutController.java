package com.example.library_webapp.controller;

import com.example.library_webapp.model.Checkout;
import com.example.library_webapp.repository.CheckoutRepository;
import com.example.library_webapp.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkouts")
@RequiredArgsConstructor
public class CheckoutController {

    private final LibraryService service;
    private final CheckoutRepository repo;

    @PostMapping("/{bookId}")
    public Checkout checkout(
            @PathVariable Long bookId,
            @AuthenticationPrincipal UserDetails user
    ) {
        return service.checkoutBook(bookId, user.getUsername());
    }

    // ✅ PAGINATED ENDPOINT
    @GetMapping
    public Page<Checkout> myCheckouts(
            @AuthenticationPrincipal UserDetails user,
            Pageable pageable
    ) {
        return repo.findByMember_Username(user.getUsername(), pageable);
    }

    @PostMapping("/return/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void returnBook(@PathVariable Long id) {
        service.returnBook(id);
    }
}
