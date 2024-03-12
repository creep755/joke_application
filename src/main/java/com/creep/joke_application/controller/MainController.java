package com.creep.joke_application.controller;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.service.JokeService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/jokes")
public class MainController {

    private final JokeService service;
    @PostMapping("post")
    public Joke postJoke(@RequestBody Joke joke) {
        return service.postJoke(joke);
    }
    @GetMapping()
    public List<Joke> getAllJokes() {
        return service.getAllJokes();
    }
    @GetMapping("random")
    public Joke getRandomJoke() {
        return service.getRandomJoke();
    }
    @GetMapping("random/{type}")
    public Joke getRandomJokeByType(@PathVariable String type) {
        return service.getRandomJokeByType(type);
    }
    @PutMapping("update-joke")
    public Joke updateJokeBySetup(@RequestBody Joke joke) {
        return service.updateJokeBySetup(joke);
    }
    @DeleteMapping("delete-joke")
    public void deleteJoke(@RequestBody Joke joke) {
        service.deleteJoke(joke);
    }
}
