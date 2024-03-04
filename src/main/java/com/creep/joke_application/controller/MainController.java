package com.creep.joke_application.controller;

import com.creep.joke_application.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/{action}")
    public String getRandomJoke(@PathVariable String action) {
        return mainService.getRandomJoke(action);
    }

}
