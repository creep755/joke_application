package com.creep.joke.repository;

import com.creep.joke.model.Joke;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Hidden
public interface JokeRepository extends JpaRepository<Joke, Long> {
    List<Joke> findAllByType(String type);
    Joke findJokeById(Long id);
    @Query(value = "SELECT j FROM Joke j WHERE j.author.nation LIKE :nation")
    List<Joke> searchAllByAuthorNation(@Param(value = "nation") String nation);
}
