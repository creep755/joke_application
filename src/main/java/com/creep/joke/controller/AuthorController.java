package com.creep.joke.controller;

import com.creep.joke.model.dto.AuthorRequestDto;
import com.creep.joke.model.dto.AuthorResponseDto;
import com.creep.joke.service.AuthorService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The type Author controller. */
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
  private final AuthorService authorService;

  /**
   * Instantiates a new Author controller.
   *
   * @param authorService the author service
   */
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  /**
   * Post author author response dto.
   *
   * @param authorRequestDto the author request dto
   * @return the author response dto
   */
  @PostMapping()
  public AuthorResponseDto postAuthor(@RequestBody AuthorRequestDto authorRequestDto) {
    return authorService.postAuthor(authorRequestDto);
  }

  /**
   * Get all authors list.
   *
   * @return the list
   */
  @GetMapping()
  public List<AuthorResponseDto> getAllAuthors() {
    return authorService.getAllAuthors();
  }

  /**
   * Get author by id author response dto.
   *
   * @param id the id
   * @return the author response dto
   */
  @GetMapping("{id}")
  public AuthorResponseDto getAuthorById(@PathVariable Long id) {
    return authorService.getAuthorById(id);
  }

  /**
   * Update author author response dto.
   *
   * @param id the id
   * @param authorRequestDto the author request dto
   * @return the author response dto
   */
  @PutMapping("{id}")
  public AuthorResponseDto updateAuthor(
      @PathVariable Long id, @RequestBody AuthorRequestDto authorRequestDto) {
    return authorService.updateAuthor(id, authorRequestDto);
  }

  /**
   * Delete author by id.
   *
   * @param id the id
   */
  @DeleteMapping("{id}")
  public void deleteAuthorById(@PathVariable Long id) {
    authorService.deleteAuthorById(id);
  }
}
