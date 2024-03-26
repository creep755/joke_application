package com.creep.joke_application.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String nation;
}
