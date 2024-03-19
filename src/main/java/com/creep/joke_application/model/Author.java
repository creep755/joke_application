package com.creep.joke_application.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String nation;
}
