package com.creep.joke_application.repository;

import com.creep.joke_application.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JokeRepository extends JpaRepository<Joke, Long> {
    List<Joke> findAllByType(String type);
    Joke findJokeById(Long id);
}
