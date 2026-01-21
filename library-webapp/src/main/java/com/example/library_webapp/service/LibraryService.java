package com.example.library_webapp.service;

import com.example.library_webapp.model.Book;
import com.example.library_webapp.model.Checkout;
import com.example.library_webapp.model.User;
import com.example.library_webapp.repository.BookRepository;
import com.example.library_webapp.repository.CheckoutRepository;
import com.example.library_webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final BookRepository bookRepo;
    private final CheckoutRepository checkoutRepo;
    private final UserRepository userRepo;

    @Transactional
    public Checkout checkoutBook(Long bookId, String username) {

        User user = userRepo.findByUsername(username).orElseThrow();

        if (checkoutRepo.countByMember_IdAndReturnDateIsNull(user.getId()) >= 5)
            throw new RuntimeException("Limit reached");

        Book book = bookRepo.findById(bookId).orElseThrow();
        if (!book.isAvailable())
            throw new RuntimeException("Unavailable");

        book.setAvailable(false);

        Checkout c = new Checkout();
        c.setBook(book);
        c.setMember(user);
        c.setCheckoutDate(LocalDate.now());
        c.setDueDate(LocalDate.now().plusDays(21));

        return checkoutRepo.save(c);
    }

    @Transactional
    public void returnBook(Long id) {
        Checkout c = checkoutRepo.findById(id).orElseThrow();
        c.setReturnDate(LocalDate.now());
        c.getBook().setAvailable(true);
    }


}
