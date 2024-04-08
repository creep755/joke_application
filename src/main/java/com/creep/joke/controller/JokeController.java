package com.creep.joke.controller;

import com.creep.joke.model.dto.JokeRequestDTO;
import com.creep.joke.model.dto.JokeResponseDTO;
import com.creep.joke.service.JokeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jokes")
public class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @PostMapping()
    public JokeResponseDTO postJoke(@RequestBody JokeRequestDTO jokeRequestDTO) {
        return jokeService.postJoke(jokeRequestDTO);
    }

    @GetMapping()
    public List<JokeResponseDTO> getAllJokes() {
        return jokeService.getAllJokes();
    }

    @GetMapping("{id}")
    public JokeResponseDTO getJokeById(@PathVariable Long id) {
        return jokeService.findJokeById(id);
    }

    @GetMapping("random")
    public JokeResponseDTO getRandomJoke() {
        return jokeService.getRandomJoke();
    }

    @GetMapping("random/{type}")
    public JokeResponseDTO getRandomJokeByType(@PathVariable String type) {
        return jokeService.getRandomJokeByType(type);
    }

    @PutMapping("{id}")
    public JokeResponseDTO updateJokeById(@PathVariable Long id, @RequestBody JokeRequestDTO jokeRequestDTO) {
        return jokeService.updateJokeById(id, jokeRequestDTO);
    }

    @PutMapping("/add-author/{jokeId}")
    public JokeResponseDTO addAuthor(@PathVariable Long jokeId, @RequestParam Long authorId) {
        return jokeService.addAuthor(jokeId, authorId);
    }

    @PutMapping("/remove-author/{id}")
    public JokeResponseDTO removeAuthor(@PathVariable Long id) {
        return jokeService.removeAuthor(id);
    }

    @DeleteMapping("{id}")
    public void deleteJoke(@PathVariable Long id) {
        jokeService.deleteJoke(id);
    }

    @GetMapping("/search")
    public List<JokeResponseDTO> searchJokesByAuthorNation(@RequestParam String nation) {
        return jokeService.searchAllByAuthorNation(nation);
    }
}
