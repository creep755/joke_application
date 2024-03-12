package com.creep.joke_application.model;


import lombok.*;

@Data
@Builder
public class Joke {
    private String type;
    private String setup;
    private String punchline;
}
