package com.creep.joke.repository;

import com.creep.joke.model.Author;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
@Hidden
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorById(Long id);
}
