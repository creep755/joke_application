package com.creep.joke.repository;

import com.creep.joke.model.Joke;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** The interface Joke repository. */
@Hidden
public interface JokeRepository extends JpaRepository<Joke, Long> {
  /**
   * Find all by type list.
   *
   * @param type the type
   * @return the list
   */
  List<Joke> findAllByType(String type);

  /**
   * Find joke by id joke.
   *
   * @param id the id
   * @return the joke
   */
  Joke findJokeById(Long id);

  /**
   * Search all by author nation list.
   *
   * @param nation the nation
   * @return the list
   */
  @Query(value = "SELECT j FROM Joke j WHERE j.author.nation LIKE :nation")
  List<Joke> searchAllByAuthorNation(@Param(value = "nation") String nation);
}
