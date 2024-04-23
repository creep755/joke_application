package com.creep.joke.service;

import com.creep.joke.model.Author;
import com.creep.joke.model.dto.AuthorRequestDto;
import com.creep.joke.model.dto.AuthorResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

/** The interface Author service. */
@Service
public interface AuthorService {
  /**
   * Post author author response dto.
   *
   * @param author the author
   * @return the author response dto
   */
  AuthorResponseDto postAuthor(AuthorRequestDto author);

  /**
   * Gets all authors.
   *
   * @return the all authors
   */
  List<AuthorResponseDto> getAllAuthors();

  /**
   * Gets author by id.
   *
   * @param id the id
   * @return the author by id
   */
  AuthorResponseDto getAuthorById(Long id);

  /**
   * Gets raw author by id.
   *
   * @param id the id
   * @return the raw author by id
   */
  Author getRawAuthorById(Long id);

  /**
   * Update author author response dto.
   *
   * @param id the id
   * @param author the author
   * @return the author response dto
   */
  AuthorResponseDto updateAuthor(Long id, AuthorRequestDto author);

  /**
   * Delete author by id.
   *
   * @param id the id
   */
  void deleteAuthorById(Long id);
}
