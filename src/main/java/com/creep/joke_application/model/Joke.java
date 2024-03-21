package com.creep.joke_application.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
    @ManyToMany(mappedBy = "jokes")
    private Set<JokeCollection> jokeCollections;
    private String setup;
    private String punchline;
}
