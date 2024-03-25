package com.creep.joke_application.service.impl;

import com.creep.joke_application.model.Author;
import com.creep.joke_application.repository.AuthorRepository;
import com.creep.joke_application.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author postAuthor(Author author) {
        //todo
        // переписать на дто
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        //todo
        // переписать на дто
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        //todo
        // переписать на дто
        return authorRepository.findAuthorById(id);
    }

    @Override
    public Author updateAuthor(Author author) {
        //todo
        // переписать на дто
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteAuthorById(id);
    }
}
