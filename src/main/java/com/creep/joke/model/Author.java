package com.creep.joke.model;

import jakarta.persistence.*;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String nation;
    @OneToMany(mappedBy = "author")
    private Set<Joke> jokes;
    @OneToMany(mappedBy = "author")
    private Set<JokeCollection> jokeCollections;
}
