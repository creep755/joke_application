package com.creep.joke_application.controller;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.JokeCollection;
import com.creep.joke_application.model.dto.JokeCollectionDTO;
import com.creep.joke_application.service.JokeCollectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collections")
public class JokeCollectionController {
    private final JokeCollectionService jokeCollectionService;
    //todo
    // добавить MainController который будет управлять всеми контроллерами (?)
    public JokeCollectionController(JokeCollectionService jokeCollectionService){
        this.jokeCollectionService = jokeCollectionService;
    }
    @PostMapping
    public JokeCollectionDTO postCollection(@RequestBody JokeCollection collection){
        //todo
        // переписать на дто
        return jokeCollectionService.postCollection(collection);
    }

    @GetMapping
    public List<JokeCollectionDTO> getAllJokeCollections(){
        return jokeCollectionService.getAllCollections();
    }

    @GetMapping("{id}")
    public JokeCollectionDTO getJokeCollectionById(@PathVariable Long id){
        return jokeCollectionService.getCollectionById(id);
    }

    @PutMapping
    public JokeCollectionDTO updateJokeCollection(@RequestBody JokeCollection collection){
        //todo
        // переписать на дто
        return jokeCollectionService.updateCollection(collection);
    }
    @PostMapping("/add-joke/{id}")
    public JokeCollectionDTO addJoke(@PathVariable Long id, @RequestBody Joke joke){
        //todo
        // переписать используя два айдишника в параметрах (коллекции и шутки)
        return jokeCollectionService.addJoke(id, joke);
    }
    @PutMapping("/remove-joke/{id}")
    public void removeJoke(@PathVariable Long id, @RequestBody Joke joke){
        //todo
        // переписать используя два айдишника в параметрах (коллекции и шутки)
        jokeCollectionService.removeJokeById(id, joke);
    }
    @DeleteMapping("{id}")
    public void deleteJokeCollectionById(@PathVariable Long id){
        jokeCollectionService.deleteCollectionById(id);
    }
}
