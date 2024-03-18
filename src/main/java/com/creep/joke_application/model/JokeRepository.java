package com.creep.joke_application.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JokeRepository extends JpaRepository<Joke, Long> {
    //Joke getRandomJoke();
    //Joke getRandomJokeByType(String type);
}
