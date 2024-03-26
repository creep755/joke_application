package com.creep.joke_application.controller;

import com.creep.joke_application.model.dto.JokeCollectionRequestDTO;
import com.creep.joke_application.model.dto.JokeCollectionResponseDTO;
import com.creep.joke_application.service.JokeCollectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collections")
public class JokeCollectionController {
    private final JokeCollectionService jokeCollectionService;
    public JokeCollectionController(JokeCollectionService jokeCollectionService){
        this.jokeCollectionService = jokeCollectionService;
    }
    @PostMapping
    public JokeCollectionResponseDTO postCollection(@RequestBody JokeCollectionRequestDTO collection){
        return jokeCollectionService.postCollection(collection);
    }

    @GetMapping
    public List<JokeCollectionResponseDTO> getAllJokeCollections(){
        return jokeCollectionService.getAllCollections();
    }

    @GetMapping("{id}")
    public JokeCollectionResponseDTO getJokeCollectionById(@PathVariable Long id){
        return jokeCollectionService.getCollectionById(id);
    }

    @PutMapping("{id}")
    public JokeCollectionResponseDTO updateJokeCollection(@PathVariable Long id, @RequestBody JokeCollectionRequestDTO jokeCollectionRequestDTO){
        return jokeCollectionService.updateCollection(id, jokeCollectionRequestDTO);
    }
    @PutMapping("/add-joke/{collectionId}")
    public JokeCollectionResponseDTO addJoke(@PathVariable Long collectionId, @RequestParam Long jokeId){
        return jokeCollectionService.addJoke(collectionId, jokeId);
    }
    @PutMapping("/remove-joke/{collectionId}")
    public JokeCollectionResponseDTO removeJoke(@PathVariable Long collectionId, @RequestParam Long jokeId){
        return jokeCollectionService.removeJoke(collectionId, jokeId);
    }
    @PutMapping("/add-author/{collectionId}")
    public JokeCollectionResponseDTO addAuthor(@PathVariable Long collectionId, @RequestParam Long jokeId){
        return jokeCollectionService.addAuthor(collectionId, jokeId);
    }
    @PutMapping("/remove-author/{id}")
    public JokeCollectionResponseDTO removeAuthor(@PathVariable Long id){
        return jokeCollectionService.removeAuthor(id);
    }
    @DeleteMapping("{id}")
    public void deleteJokeCollectionById(@PathVariable Long id){
        jokeCollectionService.deleteCollectionById(id);
    }
}
