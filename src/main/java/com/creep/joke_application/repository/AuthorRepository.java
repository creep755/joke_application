package com.creep.joke_application.repository;

import com.creep.joke_application.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorById(Long id);
    void deleteAuthorById(Long id);
}
