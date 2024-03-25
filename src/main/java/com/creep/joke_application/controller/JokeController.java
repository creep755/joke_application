package com.creep.joke_application.controller;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.dto.JokeDTO;
import com.creep.joke_application.service.JokeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jokes")
public class JokeController {
    private final JokeService jokeService;
    //todo
    // добавить MainController который будет управлять всеми контроллерами (?)
    // добавиить енд-поинт addAuthor
    public JokeController(JokeService jokeService){
        this.jokeService = jokeService;
    }
    @PostMapping()
    public JokeDTO postJoke(@RequestBody Joke joke) {
        //todo
        // переписать чтобы запрос был через дто
        return jokeService.postJoke(joke);
    }
    @GetMapping()
    public List<JokeDTO> getAllJokes() {
        return jokeService.getAllJokes();
    }
    @GetMapping("{id}")
    public JokeDTO getJokeById(@PathVariable Long id){
        return jokeService.findJokeById(id);
    }
    @GetMapping("random")
    public JokeDTO getRandomJoke() {
        return jokeService.getRandomJoke();
    }
    @GetMapping("random/{type}")
    public JokeDTO getRandomJokeByType(@PathVariable String type) {
        return jokeService.getRandomJokeByType(type);
    }
    @PutMapping()
    public JokeDTO updateJokeById(@RequestBody Joke joke) {
        // todo
        //  переписать на параментры пути и дто
        return jokeService.updateJokeById(joke);
    }
    @DeleteMapping()
    public void deleteJoke(@RequestBody Joke joke) {
        jokeService.deleteJoke(joke);
        // todo
        //  переписать чтобы запрос принимал только айди
    }
}
