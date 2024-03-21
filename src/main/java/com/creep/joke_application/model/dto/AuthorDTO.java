package com.creep.joke_application.model.dto;

import lombok.Data;

@Data
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String nation;
}
