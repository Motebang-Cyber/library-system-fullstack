package com.example.library_webapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Checkout {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User member;

    @ManyToOne
    private Book book;

    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public boolean isOverdue() {
        return returnDate == null && LocalDate.now().isAfter(dueDate);
    }
}
