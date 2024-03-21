package com.creep.joke_application.repository;

import com.creep.joke_application.model.JokeCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JokeCollectionRepository extends JpaRepository<JokeCollection, Long> {
    JokeCollection findJokeCollectionById(Long id);
}
