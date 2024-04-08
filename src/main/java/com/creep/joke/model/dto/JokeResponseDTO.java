package com.creep.joke.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeResponseDTO {
    private Long id;
    private String lang;
    private String type;
    private AuthorResponseDTO author;
    private Set<Long> jokeCollectionsId;
    private String setup;
    private String punchline;
}
