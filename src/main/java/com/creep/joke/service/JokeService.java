package com.creep.joke.service;

import com.creep.joke.model.Joke;
import com.creep.joke.model.dto.JokeRequestDTO;
import com.creep.joke.model.dto.JokeResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface JokeService {
    JokeResponseDTO postJoke(JokeRequestDTO jokeRequestDTO);
    Joke saveJoke(Joke joke);
    List<JokeResponseDTO> getAllJokes();
    JokeResponseDTO findJokeById(Long id);
    JokeResponseDTO getRandomJoke();
    JokeResponseDTO getRandomJokeByType(String type);
    Joke getRawJokeById(Long id);
    JokeResponseDTO updateJokeById(Long id, JokeRequestDTO jokeRequestDTO);
    JokeResponseDTO addAuthor(Long jokeId, Long authorId);
    JokeResponseDTO removeAuthor(Long id);
    void deleteJoke(Long id);
    List<JokeResponseDTO> searchAllByAuthorNation(String nation);

}
