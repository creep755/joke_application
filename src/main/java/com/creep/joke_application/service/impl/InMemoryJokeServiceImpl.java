package com.creep.joke_application.service.impl;

import com.creep.joke_application.mapper.JokeMapper;
import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.dto.JokeDTO;
import com.creep.joke_application.repository.InMemoryJokeDAO;
import com.creep.joke_application.service.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class InMemoryJokeServiceImpl implements JokeService {
    private final InMemoryJokeDAO repository;

    @Override
    public JokeDTO postJoke(Joke joke) {
        return JokeMapper.toDTO(repository.postJoke(joke));
    }

    @Override
    public List<JokeDTO> getAllJokes() {
        List<Joke> jokeList = repository.getAllJokes();
        List<JokeDTO> jokeDTOList = new ArrayList<>();
        for (int i = 0; i < jokeList.size(); i++) {
            jokeDTOList.add(JokeMapper.toDTO(jokeList.get(i)));
        }
        return jokeDTOList;
    }

    @Override
    public JokeDTO findJokeById(Long id) {
        return JokeMapper.toDTO(repository.findJokeById(id));
    }

    @Override
    public JokeDTO getRandomJoke() {
        return JokeMapper.toDTO(repository.getRandomJoke());
    }
    @Override
    public JokeDTO getRandomJokeByType(String type) {
        return JokeMapper.toDTO(repository.getRandomJokeByType(type));
    }

    @Override
    public JokeDTO updateJokeById(Joke newJoke) {
        return JokeMapper.toDTO(repository.updateJokeById(newJoke));
    }

    @Override
    public void deleteJoke(Joke joke) {
        repository.deleteJoke(joke);
    }
}
