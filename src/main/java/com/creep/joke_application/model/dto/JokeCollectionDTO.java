package com.creep.joke_application.model.dto;

import com.creep.joke_application.model.Author;
import com.creep.joke_application.model.Joke;
import lombok.Data;

import java.util.Set;

@Data
public class JokeCollectionDTO {
    private Long id;
    private String name;
    private String description;
    private Author author;
    private Set<Joke> jokes;
}
