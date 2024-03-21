package com.creep.joke_application.model.dto;

import com.creep.joke_application.model.Author;
import lombok.Data;

import java.util.Set;

@Data
public class JokeDTO {
    private Long id;
    private String lang;
    private String type;
    private Author author;
    private Set<Long> jokeCollectionsId;
    private String setup;
    private String punchline;
}
