package com.creep.joke.controller;

import com.creep.joke.model.dto.JokeRequestDto;
import com.creep.joke.model.dto.JokeResponseDto;
import com.creep.joke.service.JokeService;
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

/** The type Joke controller. */
@RestController
@RequestMapping("/api/v1/jokes")
public class JokeController {
  private final JokeService jokeService;

  /**
   * Instantiates a new Joke controller.
   *
   * @param jokeService the joke service
   */
  public JokeController(JokeService jokeService) {
    this.jokeService = jokeService;
  }

  /**
   * Post joke joke response dto.
   *
   * @param jokeRequestDto the joke request dto
   * @return the joke response dto
   */
  @PostMapping()
  public JokeResponseDto postJoke(@RequestBody JokeRequestDto jokeRequestDto) {
    return jokeService.postJoke(jokeRequestDto);
  }

  /**
   * Gets all jokes.
   *
   * @return the all jokes
   */
  @GetMapping()
  public List<JokeResponseDto> getAllJokes() {
    return jokeService.getAllJokes();
  }

  /**
   * Gets joke by id.
   *
   * @param id the id
   * @return the joke by id
   */
  @GetMapping("{id}")
  public JokeResponseDto getJokeById(@PathVariable Long id) {
    return jokeService.findJokeById(id);
  }

  /**
   * Gets random joke.
   *
   * @return the random joke
   */
  @GetMapping("random")
  public JokeResponseDto getRandomJoke() {
    return jokeService.getRandomJoke();
  }

  /**
   * Gets random joke by type.
   *
   * @param type the type
   * @return the random joke by type
   */
  @GetMapping("random/{type}")
  public JokeResponseDto getRandomJokeByType(@PathVariable String type) {
    return jokeService.getRandomJokeByType(type);
  }

  /**
   * Update joke by id joke response dto.
   *
   * @param id the id
   * @param jokeRequestDto the joke request dto
   * @return the joke response dto
   */
  @PutMapping("{id}")
  public JokeResponseDto updateJokeById(
      @PathVariable Long id, @RequestBody JokeRequestDto jokeRequestDto) {
    return jokeService.updateJokeById(id, jokeRequestDto);
  }

  /**
   * Add author joke response dto.
   *
   * @param jokeId the joke id
   * @param authorId the author id
   * @return the joke response dto
   */
  @PutMapping("/add-author/{jokeId}")
  public JokeResponseDto addAuthor(@PathVariable Long jokeId, @RequestParam Long authorId) {
    return jokeService.addAuthor(jokeId, authorId);
  }

  /**
   * Remove author joke response dto.
   *
   * @param id the id
   * @return the joke response dto
   */
  @PutMapping("/remove-author/{id}")
  public JokeResponseDto removeAuthor(@PathVariable Long id) {
    return jokeService.removeAuthor(id);
  }

  /**
   * Delete joke.
   *
   * @param id the id
   */
  @DeleteMapping("{id}")
  public void deleteJoke(@PathVariable Long id) {
    jokeService.deleteJoke(id);
  }

  /**
   * Search jokes by author nation list.
   *
   * @param nation the nation
   * @return the list
   */
  @GetMapping("/search")
  public List<JokeResponseDto> searchJokesByAuthorNation(@RequestParam String nation) {
    return jokeService.searchAllByAuthorNation(nation);
  }
}
