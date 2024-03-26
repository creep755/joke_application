package com.creep.joke_application.service;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.dto.JokeRequestDTO;
import com.creep.joke_application.model.dto.JokeResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface JokeService {
    JokeResponseDTO postJoke(JokeRequestDTO jokeRequestDTO);
    List<JokeResponseDTO> getAllJokes();
    JokeResponseDTO findJokeById(Long id);
    JokeResponseDTO getRandomJoke();
    JokeResponseDTO getRandomJokeByType(String type);
    Joke getRawJokeById(Long id);
    JokeResponseDTO updateJokeById(Long id, JokeRequestDTO jokeRequestDTO);
    JokeResponseDTO addAuthor(Long jokeId, Long authorId);
    JokeResponseDTO removeAuthor(Long id);
    void deleteJoke(Long id);

}
