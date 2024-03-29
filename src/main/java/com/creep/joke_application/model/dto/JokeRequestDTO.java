package com.creep.joke_application.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeRequestDTO {
    private String lang;
    private String type;
    private String setup;
    private String punchline;
}
