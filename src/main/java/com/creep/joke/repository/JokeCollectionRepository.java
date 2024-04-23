package com.creep.joke.repository;

import com.creep.joke.model.JokeCollection;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

/** The interface Joke collection repository. */
@Hidden
public interface JokeCollectionRepository extends JpaRepository<JokeCollection, Long> {
  /**
   * Find joke collection by id joke collection.
   *
   * @param id the id
   * @return the joke collection
   */
  JokeCollection findJokeCollectionById(Long id);
}
