package com.creep.joke_application.service;

import com.creep.joke_application.model.Author;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AuthorService {
    Author postAuthor(Author author);
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    Author updateAuthor(Author author);
    void deleteAuthorById(Long id);
}
