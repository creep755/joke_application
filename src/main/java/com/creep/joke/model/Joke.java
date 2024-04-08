package com.creep.joke.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
