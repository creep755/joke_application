package com.creep.joke_application.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeCollectionRequestDTO {
    private String title;
    private String description;
}
