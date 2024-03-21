package com.creep.joke_application.controller;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.dto.JokeDTO;
import com.creep.joke_application.service.JokeService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/jokes")
public class JokeController {

    private final JokeService service;
    @PostMapping()
    public JokeDTO postJoke(@RequestBody Joke joke) {
        return service.postJoke(joke);
    }
    @GetMapping()
    public List<JokeDTO> getAllJokes() {
        return service.getAllJokes();
    }
    @GetMapping("random")
    public JokeDTO getRandomJoke() {
        return service.getRandomJoke();
    }
    @GetMapping("random/{type}")
    public JokeDTO getRandomJokeByType(@PathVariable String type) {
        return service.getRandomJokeByType(type);
    }
    @PutMapping()
    public JokeDTO updateJokeById(@RequestBody Joke joke) {
        return service.updateJokeById(joke);
    }
    @DeleteMapping()
    public void deleteJoke(@RequestBody Joke joke) {
        service.deleteJoke(joke);
    }
}
