package com.creep.joke_application.controller;

import com.creep.joke_application.service.MainService;

import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequestMapping("/api/v1")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/{action}")
    public String getRandomJoke(@PathVariable String action) {
        return mainService.getRandomJoke(action);
    }
}
