package com.example.library_webapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Author {

    @Id @GeneratedValue
    private Long id;
    private String name;
}
