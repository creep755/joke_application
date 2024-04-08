package com.creep.joke.service;

import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.JokeCollectionRequestDTO;
import com.creep.joke.model.dto.JokeCollectionResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JokeCollectionService {
    JokeCollectionResponseDTO postCollection(JokeCollectionRequestDTO jokeCollectionRequestDTO);
    List<JokeCollectionResponseDTO> getAllCollections();
    JokeCollectionResponseDTO getCollectionById(Long id);
    JokeCollectionResponseDTO updateCollection(Long id, JokeCollectionRequestDTO jokeCollectionRequestDTO);
    JokeCollectionResponseDTO addJoke(Long collectionId, Long jokeId);
    JokeCollectionResponseDTO removeJoke(Long collectionId, Long jokeId);
    JokeCollectionResponseDTO addAuthor(Long collectionId, Long authorId);
    JokeCollectionResponseDTO removeAuthor(Long id);
    JokeCollection save(JokeCollection jokeCollection);
    void deleteCollectionById(Long id);
}
