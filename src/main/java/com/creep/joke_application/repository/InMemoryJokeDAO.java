package com.creep.joke_application.repository;

import com.creep.joke_application.model.Joke;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Repository
public class InMemoryJokeDAO {
    private final List<Joke> jokeList = new ArrayList<>();
    private Random rand = new Random();
    public Joke postJoke(Joke joke) {
        jokeList.add(joke);
        return joke;
    }
    public List<Joke> getAllJokes() {
        return jokeList;
    }
    public Joke getRandomJoke() {
        return jokeList.get(rand.nextInt(0,jokeList.size()));
    }
    public Joke getRandomJokeByType(String type) {
        return jokeList.stream()
                .filter(element -> element.getType().equals(type))
                .toList()
                .get(rand.nextInt(0, jokeList.size()));

    }
    public Joke updateJokeById(Joke joke) {
        var jokeIndex = IntStream.range(0, jokeList.size() -1)
                .filter(index -> jokeList.get(index).getId().equals(joke.getId()))
                .findFirst()
                .orElse(-1);
        if (jokeIndex > -1) {
            jokeList.set(jokeIndex, joke);
            return joke;
        }
        return null;
    }
    public void deleteJoke(Joke joke) {
        jokeList.remove(joke);
    }
}
