package com.creep.joke_application.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeDTO {
    //todo переписать дто:
    // сделать внутри два класса: .Response и .Request,
    // которые содержат только нужные поля
    private Long id;
    private String lang;
    private String type;
    private AuthorDTO author;
    private Set<Long> jokeCollectionsId;
    private String setup;
    private String punchline;
}
