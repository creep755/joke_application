package com.creep.joke_application.controller;

import com.creep.joke_application.model.Author;
import com.creep.joke_application.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService service;

    @PostMapping()
    public Author postAuthor(@RequestBody Author author){
        return service.postAuthor(author);
    }
    @GetMapping()
    public List<Author> getAllAuthors(){
        return service.getAllAuthors();
    }
    @GetMapping("{id}")
    public Author getAuthorById(@PathVariable Long id){
        return service.getAuthorById(id);
    }
    @PutMapping()
    public Author updateAuthor(@RequestBody Author author){
        return service.updateAuthor(author);
    }
    @DeleteMapping("{id}")
    public void deleteAuthorById(@PathVariable Long id){
        service.deleteAuthorById(id);
    }
}