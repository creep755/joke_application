package com.creep.joke_application.service.impl;

import com.creep.joke_application.mapper.JokeMapper;
import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.dto.JokeDTO;
import com.creep.joke_application.repository.JokeRepository;
import com.creep.joke_application.service.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
@Primary
public class JokeServiceImpl implements JokeService {

    private final JokeRepository repository;
    private Random rand = new Random();

    @Override
    public JokeDTO postJoke(Joke joke) {
        return JokeMapper.toDTO(repository.save(joke));
    }

    @Override
    public List<JokeDTO> getAllJokes() {
        List<Joke> jokeList = repository.findAll();
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
        var jokeList = repository.findAll();
        return JokeMapper.toDTO(jokeList.get(rand.nextInt(0,jokeList.size())));
    }

   @Override
    public JokeDTO getRandomJokeByType(String type) {
       var jokeList = repository.findAllByType(type);
       return JokeMapper.toDTO(jokeList.get(rand.nextInt(0, jokeList.size())));
    }

    @Override
    public JokeDTO updateJokeById(Joke newJoke) {
        return JokeMapper.toDTO(repository.save(newJoke));
    }

    @Override
    public void deleteJoke(Joke joke) {
        repository.delete(joke);
    }
}
