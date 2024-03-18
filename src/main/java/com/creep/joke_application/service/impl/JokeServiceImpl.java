package com.creep.joke_application.service.impl;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.JokeRepository;
import com.creep.joke_application.service.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class JokeServiceImpl implements JokeService {

    private final JokeRepository repository;

    @Override
    public Joke postJoke(Joke joke) {
        return repository.save(joke);
    }

    @Override
    public List<Joke> getAllJokes() {
        return repository.findAll();
    }

/*    @Override
    public Joke getRandomJoke() {
        return repository.getRandomJoke();
    }*/

 /*   @Override
    public Joke getRandomJokeByType(String type) {
        return repository.getRandomJokeByType(type);
    }*/

    @Override
    public Joke updateJokeById(Joke newJoke) {
        return repository.save(newJoke);
    }

    @Override
    public void deleteJoke(Joke joke) {
        repository.delete(joke);
    }
}
