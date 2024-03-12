package com.creep.joke_application.service;

import com.creep.joke_application.model.Joke;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface JokeService {
    Joke postJoke(Joke joke);
    List<Joke> getAllJokes();
    Joke getRandomJoke();
    Joke getRandomJokeByType(String type);
    Joke updateJokeBySetup(Joke joke);
    void deleteJoke(Joke joke);

}
