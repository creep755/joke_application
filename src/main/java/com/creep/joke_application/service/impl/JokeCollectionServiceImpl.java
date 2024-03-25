package com.creep.joke_application.service.impl;

import com.creep.joke_application.mapper.JokeCollectionMapper;
import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.JokeCollection;
import com.creep.joke_application.model.dto.JokeCollectionDTO;
import com.creep.joke_application.repository.JokeCollectionRepository;
import com.creep.joke_application.service.JokeCollectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JokeCollectionServiceImpl implements JokeCollectionService {

    //todo
    // добавить методы добавления/удаления автора
    private final JokeCollectionRepository jokeCollectionRepository;
    @Override
    public JokeCollectionDTO postCollection(JokeCollection collection) {
        //todo
        // переписать на дто
        // пофиксить ошибку 500
        return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(collection));
    }

    @Override
    public List<JokeCollectionDTO> getAllCollections() {
        //todo
        // переписать чтобы было красиво (мб через stream)
        List<JokeCollectionDTO> jokeCollectionDTOList = new ArrayList<>();
        List<JokeCollection> jokeCollectionList = jokeCollectionRepository.findAll();
        for (JokeCollection jokeCollection : jokeCollectionList) {
            jokeCollectionDTOList.add(JokeCollectionMapper.toDTO(jokeCollection));
        }
        return jokeCollectionDTOList;
    }

    @Override
    public JokeCollectionDTO getCollectionById(Long id) {
        return JokeCollectionMapper.toDTO(jokeCollectionRepository.findJokeCollectionById(id));
    }

    @Override
    public JokeCollectionDTO updateCollection(JokeCollection collection) {
        //todo
        // переписать на дто
        return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(collection));
    }

    @Override
    public JokeCollectionDTO addJoke(Long id, Joke joke) {
        //todo
        // переписать на дто
       JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
       jokeCollection.getJokes().add(joke);
       return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(jokeCollection));
    }

    @Override
    public void removeJokeById(Long id, Joke joke) {
        //todo
        // переписать на два айдишника (шутки и коллекции)
        JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
        jokeCollection.getJokes().remove(joke);
        jokeCollectionRepository.save(jokeCollection);
    }

    @Override
    public void deleteCollectionById(Long id) {
        //todo
        // сначала проверить есть ли связи (автор, шутки) если есть то разорвать (?)
        jokeCollectionRepository.deleteById(id);
    }
}
