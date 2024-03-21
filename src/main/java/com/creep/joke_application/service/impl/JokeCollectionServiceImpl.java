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

    private final JokeCollectionRepository repository;
    @Override
    public JokeCollectionDTO postCollection(JokeCollection collection) {
        return JokeCollectionMapper.toDTO(repository.save(collection));
    }

    @Override
    public List<JokeCollectionDTO> getAllCollections() {
        List<JokeCollectionDTO> jokeCollectionDTOList = new ArrayList<>();
        List<JokeCollection> jokeCollectionList = repository.findAll();
        for (int i = 0; i < jokeCollectionList.size(); i++){
            jokeCollectionDTOList.add(JokeCollectionMapper.toDTO(jokeCollectionList.get(i)));
        }
        return jokeCollectionDTOList;
    }

    @Override
    public JokeCollectionDTO getCollectionById(Long id) {
        return JokeCollectionMapper.toDTO(repository.findJokeCollectionById(id));
    }

    @Override
    public JokeCollectionDTO updateCollection(JokeCollection collection) {
        return JokeCollectionMapper.toDTO(repository.save(collection));
    }

    @Override
    public JokeCollectionDTO addJoke(Long id, Joke joke) {
       JokeCollection jokeCollection = repository.findJokeCollectionById(id);
       jokeCollection.getJokes().add(joke);
       return JokeCollectionMapper.toDTO(repository.save(jokeCollection));
    }

    @Override
    public void removeJokeById(Long id, Joke joke) {
        JokeCollection jokeCollection = repository.findJokeCollectionById(id);
        jokeCollection.getJokes().remove(joke);
        repository.save(jokeCollection);
    }

    @Override
    public void deleteCollectionById(Long id) {
        repository.deleteById(id);
    }
}
