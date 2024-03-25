package com.creep.joke_application.service;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.JokeCollection;
import com.creep.joke_application.model.dto.JokeCollectionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JokeCollectionService {
    //todo
    // Use DTO!
    JokeCollectionDTO postCollection(JokeCollection collection);
    List<JokeCollectionDTO> getAllCollections();
    JokeCollectionDTO getCollectionById(Long id);
    JokeCollectionDTO updateCollection(JokeCollection collection);
    JokeCollectionDTO addJoke(Long id, Joke joke);
    void removeJokeById(Long id, Joke joke);
    void deleteCollectionById(Long id);
}
