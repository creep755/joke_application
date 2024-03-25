package com.creep.joke_application.controller;

import com.creep.joke_application.model.Author;
import com.creep.joke_application.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    //todo
    // добавить MainController который будет управлять всеми контроллерами (?)
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @PostMapping()
    public Author postAuthor(@RequestBody Author author){
        //todo
        // переписать на дто
        return authorService.postAuthor(author);
    }
    @GetMapping()
    public List<Author> getAllAuthors(){
        //todo
        // переписать на дто
        return authorService.getAllAuthors();
    }
    @GetMapping("{id}")
    public Author getAuthorById(@PathVariable Long id){
        //todo
        // переписать на дто
        return authorService.getAuthorById(id);
    }
    @PutMapping()
    public Author updateAuthor(@RequestBody Author author){
        //todo
        // переписать на дто
        return authorService.updateAuthor(author);
    }
    @DeleteMapping("{id}")
    public void deleteAuthorById(@PathVariable Long id){
        //todo
        // переписать на дто
        authorService.deleteAuthorById(id);
    }
}