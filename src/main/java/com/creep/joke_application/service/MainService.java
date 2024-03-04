package com.creep.joke_application.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MainService {

    private static final String JOKE_API_URL = "https://official-joke-api.appspot.com/jokes/";

    private final RestTemplate restTemplate;

    public MainService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRandomJoke(String action) {
        String url = JOKE_API_URL + action;
        return restTemplate.getForObject(url, String.class);
    }
}
