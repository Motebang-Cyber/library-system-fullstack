package com.example.library_webapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private boolean available = true;

    @ManyToOne
    private Author author;

}
