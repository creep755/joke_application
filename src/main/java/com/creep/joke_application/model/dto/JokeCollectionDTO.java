package com.creep.joke_application.model.dto;

import com.creep.joke_application.model.Author;
import com.creep.joke_application.model.Joke;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeCollectionDTO {
    private Long id;
    private String name;
    private String description;
    private AuthorDTO author;
    private Set<JokeDTO> jokes;
}
