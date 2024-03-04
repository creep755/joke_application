package com.creep.joke_application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MainService {

    private static  String jokeApiUrl = "https://official-joke-api.appspot.com/jokes/";

    private final RestTemplate restTemplate;

    public String getRandomJoke(String action) {
        String url = jokeApiUrl + action;
        return restTemplate.getForObject(url, String.class);
    }

    public String getRandomJoke() {
        String url = jokeApiUrl + "random";
        return restTemplate.getForObject(url, String.class);
    }
}
