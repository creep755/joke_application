package com.creep.joke_application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MainService {

    private static final String JOKE_API_URL = "https://official-joke-api.appspot.com/jokes/{action}";

    private final RestTemplate restTemplate;

    public String getRandomJoke(String action) {

        String url = JOKE_API_URL.replace("{action}",action);
        return restTemplate.getForObject(url, String.class);
    }

}
