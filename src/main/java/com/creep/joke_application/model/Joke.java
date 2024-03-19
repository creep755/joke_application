package com.creep.joke_application.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "jokes")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lang;
    private String type;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private String setup;
    private String punchline;
}
