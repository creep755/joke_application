package com.creep.joke_application.model.dto;

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
