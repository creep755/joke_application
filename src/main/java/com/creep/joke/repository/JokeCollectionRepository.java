package com.creep.joke.repository;

import com.creep.joke.model.JokeCollection;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
@Hidden
public interface JokeCollectionRepository extends JpaRepository<JokeCollection, Long> {
    JokeCollection findJokeCollectionById(Long id);
}
