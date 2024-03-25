package com.creep.joke_application.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String nation;
    //todo
    // добавить one-to-many поле с шутками (соответсвенно поменять дто и Joke)
    // анологично поле с коллекциями
}
