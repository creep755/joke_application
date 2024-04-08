package com.creep.joke.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDTO {
    private String firstName;
    private String lastName;
    private String nation;
}
