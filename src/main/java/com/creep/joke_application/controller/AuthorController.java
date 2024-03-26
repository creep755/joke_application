package com.creep.joke_application.controller;

import com.creep.joke_application.model.dto.AuthorRequestDTO;
import com.creep.joke_application.model.dto.AuthorResponseDTO;
import com.creep.joke_application.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @PostMapping()
    public AuthorResponseDTO postAuthor(@RequestBody AuthorRequestDTO authorRequestDTO){
        return authorService.postAuthor(authorRequestDTO);
    }
    @GetMapping()
    public List<AuthorResponseDTO> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("{id}")
    public AuthorResponseDTO getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }
    @PutMapping("{id}")
    public AuthorResponseDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO authorRequestDTO){
        return authorService.updateAuthor(id, authorRequestDTO);
    }
    @DeleteMapping("{id}")
    public void deleteAuthorById(@PathVariable Long id){
        authorService.deleteAuthorById(id);
    }
}