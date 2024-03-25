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

    private final JokeRepository jokeRepository;
    private Random rand;
    //todo
    // добавить методы добавления/удаления автора и коллекции
    @Override
    public JokeDTO postJoke(Joke joke) {
        //todo
        // переписать с использованием дто в параметрах
        // пофиксить ошибку 500
        return JokeMapper.toDTO(jokeRepository.save(joke));
    }

    @Override
    public List<JokeDTO> getAllJokes() {
        List<Joke> jokeList = jokeRepository.findAll();
        List<JokeDTO> jokeDTOList = new ArrayList<>();
        for (Joke joke : jokeList) {
            jokeDTOList.add(JokeMapper.toDTO(joke));
        }
        return jokeDTOList;
    }

    @Override
    public JokeDTO findJokeById(Long id) {
         return JokeMapper.toDTO(jokeRepository.findJokeById(id));
    }

    @Override
    public JokeDTO getRandomJoke() {
        var jokeList = jokeRepository.findAll();
        return JokeMapper.toDTO(jokeList.get(rand.nextInt(0,jokeList.size())));
    }

   @Override
    public JokeDTO getRandomJokeByType(String type) {
       var jokeList = jokeRepository.findAllByType(type);
       return JokeMapper.toDTO(jokeList.get(rand.nextInt(0, jokeList.size())));
    }

    @Override
    public JokeDTO updateJokeById(Joke newJoke) {
        //todo
        // переписать с использованием дто и айди в аргументах
        return JokeMapper.toDTO(jokeRepository.save(newJoke));
    }

    @Override
    public void deleteJoke(Joke joke) {
        //todo
        // переписать с использованием в параметрах только айди
        jokeRepository.delete(joke);
    }
}
