package com.creep.joke.service;

import com.creep.joke.model.Joke;
import com.creep.joke.model.dto.JokeRequestDto;
import com.creep.joke.model.dto.JokeResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

/** The interface Joke service. */
@Service
public interface JokeService {
  /**
   * Post joke joke response dto.
   *
   * @param jokeRequestDto the joke request dto
   * @return the joke response dto
   */
  JokeResponseDto postJoke(JokeRequestDto jokeRequestDto);

  /**
   * Save joke joke.
   *
   * @param joke the joke
   * @return the joke
   */
  Joke saveJoke(Joke joke);

  /**
   * Gets all jokes.
   *
   * @return the all jokes
   */
  List<JokeResponseDto> getAllJokes();

  /**
   * Find joke by id joke response dto.
   *
   * @param id the id
   * @return the joke response dto
   */
  JokeResponseDto findJokeById(Long id);

  /**
   * Gets random joke.
   *
   * @return the random joke
   */
  JokeResponseDto getRandomJoke();

  /**
   * Gets random joke by type.
   *
   * @param type the type
   * @return the random joke by type
   */
  JokeResponseDto getRandomJokeByType(String type);

  /**
   * Gets raw joke by id.
   *
   * @param id the id
   * @return the raw joke by id
   */
  Joke getRawJokeById(Long id);

  /**
   * Update joke by id joke response dto.
   *
   * @param id the id
   * @param jokeRequestDto the joke request dto
   * @return the joke response dto
   */
  JokeResponseDto updateJokeById(Long id, JokeRequestDto jokeRequestDto);

  /**
   * Add author joke response dto.
   *
   * @param jokeId the joke id
   * @param authorId the author id
   * @return the joke response dto
   */
  JokeResponseDto addAuthor(Long jokeId, Long authorId);

  /**
   * Remove author joke response dto.
   *
   * @param id the id
   * @return the joke response dto
   */
  JokeResponseDto removeAuthor(Long id);

  /**
   * Delete joke.
   *
   * @param id the id
   */
  void deleteJoke(Long id);

  /**
   * Search all by author nation list.
   *
   * @param nation the nation
   * @return the list
   */
  List<JokeResponseDto> searchAllByAuthorNation(String nation);
}
