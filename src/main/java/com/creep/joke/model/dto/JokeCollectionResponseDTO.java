package com.creep.joke.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeCollectionResponseDTO {
    private Long id;
    private String title;
    private String description;
    private AuthorResponseDTO author;
    private Set<JokeResponseDTO> jokes;
}
