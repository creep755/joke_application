package com.creep.joke.repository;

import com.creep.joke.model.Author;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

/** The interface Author repository. */
@Hidden
public interface AuthorRepository extends JpaRepository<Author, Long> {
  /**
   * Find author by id author.
   *
   * @param id the id
   * @return the author
   */
  Author findAuthorById(Long id);
}
