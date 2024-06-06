package com.creep.joke.service;

import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.JokeCollectionRequestDto;
import com.creep.joke.model.dto.JokeCollectionResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

/** The interface Joke collection service. */
@Service
public interface JokeCollectionService {
  /**
   * Post collection joke collection response dto.
   *
   * @param jokeCollectionRequestDto the joke collection request dto
   * @return the joke collection response dto
   */
  JokeCollectionResponseDto postCollection(JokeCollectionRequestDto jokeCollectionRequestDto);

  /**
   * Gets all collections.
   *
   * @return the all collections
   */
  List<JokeCollectionResponseDto> getAllCollections();

  /**
   * Gets collection by id.
   *
   * @param id the id
   * @return the collection by id
   */
  JokeCollectionResponseDto getCollectionById(Long id);

  List<JokeCollectionResponseDto> getCollectionsByTitle(String title);
  /**
   * Update collection joke collection response dto.
   *
   * @param id the id
   * @param jokeCollectionRequestDto the joke collection request dto
   * @return the joke collection response dto
   */
  JokeCollectionResponseDto updateCollection(
      Long id, JokeCollectionRequestDto jokeCollectionRequestDto);

  /**
   * Add joke joke collection response dto.
   *
   * @param collectionId the collection id
   * @param jokeId the joke id
   * @return the joke collection response dto
   */
  JokeCollectionResponseDto addJoke(Long collectionId, Long jokeId);

  /**
   * Remove joke joke collection response dto.
   *
   * @param collectionId the collection id
   * @param jokeId the joke id
   * @return the joke collection response dto
   */
  JokeCollectionResponseDto removeJoke(Long collectionId, Long jokeId);

  /**
   * Add author joke collection response dto.
   *
   * @param collectionId the collection id
   * @param authorId the author id
   * @return the joke collection response dto
   */
  JokeCollectionResponseDto addAuthor(Long collectionId, Long authorId);

  /**
   * Remove author joke collection response dto.
   *
   * @param id the id
   * @return the joke collection response dto
   */
  JokeCollectionResponseDto removeAuthor(Long id);

  /**
   * Save joke collection.
   *
   * @param jokeCollection the joke collection
   * @return the joke collection
   */
  JokeCollection save(JokeCollection jokeCollection);

  /**
   * Delete collection by id.
   *
   * @param id the id
   */
  void deleteCollectionById(Long id);
}
