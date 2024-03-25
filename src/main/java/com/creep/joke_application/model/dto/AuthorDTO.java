package com.creep.joke_application.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    //todo
    // переписать дто:
    // сделать внутри два класса: .Response и .Request,
    // которые содержат только нужные поля
    private Long id;
    private String firstName;
    private String lastName;
    private String nation;
}
