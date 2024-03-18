package com.creep.joke_application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "jokes")
public class Joke {
    @Id
    @GeneratedValue
    private Long id;
    private String lang;
    private String type;
    private String author;
    private String setup;
    private String punchline;
}
