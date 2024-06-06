package com.creep.joke.controller;

import com.creep.joke.model.dto.JokeCollectionRequestDto;
import com.creep.joke.model.dto.JokeCollectionResponseDto;
import com.creep.joke.service.JokeCollectionService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** The type Joke collection controller. */
@RestController
@RequestMapping("/api/v1/collections")
public class JokeCollectionController {
  private final JokeCollectionService jokeCollectionService;

  /**
   * Instantiates a new Joke collection controller.
   *
   * @param jokeCollectionService the joke collection service
   */
  public JokeCollectionController(JokeCollectionService jokeCollectionService) {
    this.jokeCollectionService = jokeCollectionService;
  }

  /**
   * Post collection joke collection response dto.
   *
   * @param collection the collection
   * @return the joke collection response dto
   */
  @PostMapping
  public JokeCollectionResponseDto postCollection(
      @RequestBody JokeCollectionRequestDto collection) {
    return jokeCollectionService.postCollection(collection);
  }

  /**
   * Get all joke collections list.
   *
   * @return the list
   */
  @GetMapping
  public List<JokeCollectionResponseDto> getAllJokeCollections() {
    return jokeCollectionService.getAllCollections();
  }

  /**
   * Get joke collection by id joke collection response dto.
   *
   * @param id the id
   * @return the joke collection response dto
   */
  @GetMapping("{id}")
  public JokeCollectionResponseDto getJokeCollectionById(@PathVariable Long id) {
    return jokeCollectionService.getCollectionById(id);
  }

  @GetMapping("/get-by-title")
  public List<JokeCollectionResponseDto> getJokeCollectionsByTitle(@RequestParam String title){
    return jokeCollectionService.getCollectionsByTitle(title);
  }
  /**
   * Update joke collection joke collection response dto.
   *
   * @param id the id
   * @param jokeCollectionRequestDto the joke collection request dto
   * @return the joke collection response dto
   */
  @PutMapping("{id}")
  public JokeCollectionResponseDto updateJokeCollection(
      @PathVariable Long id, @RequestBody JokeCollectionRequestDto jokeCollectionRequestDto) {
    return jokeCollectionService.updateCollection(id, jokeCollectionRequestDto);
  }

  /**
   * Add joke joke collection response dto.
   *
   * @param collectionId the collection id
   * @param jokeId the joke id
   * @return the joke collection response dto
   */
  @PutMapping("/add-joke/{collectionId}")
  public JokeCollectionResponseDto addJoke(
      @PathVariable Long collectionId, @RequestParam Long jokeId) {
    return jokeCollectionService.addJoke(collectionId, jokeId);
  }

  /**
   * Remove joke joke collection response dto.
   *
   * @param collectionId the collection id
   * @param jokeId the joke id
   * @return the joke collection response dto
   */
  @PutMapping("/remove-joke/{collectionId}")
  public JokeCollectionResponseDto removeJoke(
      @PathVariable Long collectionId, @RequestParam Long jokeId) {
    return jokeCollectionService.removeJoke(collectionId, jokeId);
  }

  /**
   * Add author joke collection response dto.
   *
   * @param collectionId the collection id
   * @param authorId the author id
   * @return the joke collection response dto
   */
  @PutMapping("/add-author/{collectionId}")
  public JokeCollectionResponseDto addAuthor(
      @PathVariable Long collectionId, @RequestParam Long authorId) {
    return jokeCollectionService.addAuthor(collectionId, authorId);
  }

  /**
   * Remove author joke collection response dto.
   *
   * @param id the id
   * @return the joke collection response dto
   */
  @PutMapping("/remove-author/{id}")
  public JokeCollectionResponseDto removeAuthor(@PathVariable Long id) {
    return jokeCollectionService.removeAuthor(id);
  }

  /**
   * Delete joke collection by id.
   *
   * @param id the id
   */
  @DeleteMapping("{id}")
  public void deleteJokeCollectionById(@PathVariable Long id) {
    jokeCollectionService.deleteCollectionById(id);
  }
}
