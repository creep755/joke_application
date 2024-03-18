package com.creep.joke_application.service.impl;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.repository.JokeRepository;
import com.creep.joke_application.service.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
@Primary
public class JokeServiceImpl implements JokeService {

    private final JokeRepository repository;
    private Random rand = new Random();

    @Override
    public Joke postJoke(Joke joke) {
        return repository.save(joke);
    }

    @Override
    public List<Joke> getAllJokes() {
        return repository.findAll();
    }

    @Override
    public Joke getRandomJoke() {
        var jokeList = repository.findAll();
        return jokeList.get(rand.nextInt(0,jokeList.size()));
    }

   @Override
    public Joke getRandomJokeByType(String type) {
       var jokeList = repository.findAllByType(type);
       return jokeList.get(rand.nextInt(0, jokeList.size()));
    }

    @Override
    public Joke updateJokeById(Joke newJoke) {
        return repository.save(newJoke);
    }

    @Override
    public void deleteJoke(Joke joke) {
        repository.delete(joke);
    }
}
