package com.creep.joke_application.service.impl;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.repository.InMemoryJokeDAO;
import com.creep.joke_application.service.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class InMemoryJokeServiceImpl implements JokeService {
    private final InMemoryJokeDAO repository;

    @Override
    public Joke postJoke(Joke joke) {
        return repository.postJoke(joke);
    }

    @Override
    public List<Joke> getAllJokes() {
        return repository.getAllJokes();
    }

    @Override
    public Joke getRandomJoke() {
        return repository.getRandomJoke();
    }

    @Override
    public Joke getRandomJokeByType(String type) {
        return repository.getRandomJokeByType(type);
    }

    @Override
    public Joke updateJokeBySetup(Joke joke) {
        return repository.updateJokeBySetup(joke);
    }

    @Override
    public void deleteJoke(Joke joke) {
        repository.deleteJoke(joke);
    }
}
