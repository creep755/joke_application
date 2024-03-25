package com.creep.joke_application.service;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.dto.JokeDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface JokeService {
    //todo
    // Use DTO!
    JokeDTO postJoke(Joke joke);
    List<JokeDTO> getAllJokes();
    JokeDTO findJokeById(Long id);
    JokeDTO getRandomJoke();
    JokeDTO getRandomJokeByType(String type);
    JokeDTO updateJokeById(Joke newJoke);
    void deleteJoke(Joke joke);

}
