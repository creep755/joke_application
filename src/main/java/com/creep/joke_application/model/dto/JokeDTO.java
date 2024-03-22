package com.creep.joke_application.model.dto;

import com.creep.joke_application.model.Author;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeDTO {
    private Long id;
    private String lang;
    private String type;
    private AuthorDTO author;
    private Set<Long> jokeCollectionsId;
    private String setup;
    private String punchline;
}
