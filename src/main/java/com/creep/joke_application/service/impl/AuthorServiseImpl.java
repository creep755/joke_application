package com.creep.joke_application.service.impl;

import com.creep.joke_application.model.Author;
import com.creep.joke_application.repository.AuthorRepository;
import com.creep.joke_application.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiseImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public Author postAuthor(Author author) {
        return repository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return repository.findAuthorById(id);
    }

    @Override
    public Author updateAuthor(Author author) {
        return repository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        repository.deleteAuthorById(id);
    }
}
