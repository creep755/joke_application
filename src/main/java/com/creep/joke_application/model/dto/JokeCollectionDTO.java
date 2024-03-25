package com.creep.joke_application.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeCollectionDTO {
    //todo переписать дто:
    // сделать внутри два класса: .Response и .Request,
    // которые содержат только нужные поля
    private Long id;
    private String name;
    private String description;
    private AuthorDTO author;
    private Set<JokeDTO> jokes;
}
