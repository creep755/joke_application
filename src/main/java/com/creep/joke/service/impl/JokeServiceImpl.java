package com.creep.joke.service.impl;

import com.creep.joke.cache.InMemoryMap;
import com.creep.joke.mapper.JokeMapper;
import com.creep.joke.model.Author;
import com.creep.joke.model.Joke;
import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.JokeRequestDTO;
import com.creep.joke.model.dto.JokeResponseDTO;
import com.creep.joke.repository.JokeRepository;
import com.creep.joke.service.AuthorService;
import com.creep.joke.service.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class JokeServiceImpl implements JokeService {

    private final JokeRepository jokeRepository;
    private Random rand;
    private final AuthorService authorService;
    private final InMemoryMap cache;
    private static final String JOKE_KEY_PREFIX = "joke id ";
    private static final String AUTHOR_KEY_PREFIX = "author id ";
    @Override
    public JokeResponseDTO postJoke(JokeRequestDTO jokeRequestDTO) {
        Joke joke = JokeMapper.toEntity(jokeRequestDTO);
        if (joke == null) {
            return null;
        }
        return JokeMapper.toDTO(jokeRepository.save(joke));
    }

    @Override
    public Joke saveJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    @Override
    public List<JokeResponseDTO> getAllJokes() {
        List<Joke> jokeList = jokeRepository.findAll();
        List<JokeResponseDTO> jokeResponseDTOList = new ArrayList<>();
        for (Joke joke : jokeList) {
            jokeResponseDTOList.add(JokeMapper.toDTO(joke));
        }
        return jokeResponseDTOList;
    }

    @Override
    public JokeResponseDTO findJokeById(Long id) {
        if (cache.containsKey(JOKE_KEY_PREFIX + id)){
            return JokeMapper.toDTO((Joke) cache.get(JOKE_KEY_PREFIX + id));
        }
         return JokeMapper.toDTO(jokeRepository.findJokeById(id));
    }

    @Override
    public JokeResponseDTO getRandomJoke() {
        var jokeList = jokeRepository.findAll();
        return JokeMapper.toDTO(jokeList.get(rand.nextInt(0,jokeList.size())));
    }

   @Override
    public JokeResponseDTO getRandomJokeByType(String type) {
       var jokeList = jokeRepository.findAllByType(type);
       return JokeMapper.toDTO(jokeList.get(rand.nextInt(0, jokeList.size())));
    }

    @Override
    public Joke getRawJokeById(Long id) {
        return jokeRepository.findJokeById(id);
    }

    @Override
    public JokeResponseDTO updateJokeById(Long id, JokeRequestDTO jokeRequestDTO) {
        Joke joke = jokeRepository.findJokeById(id);
        if (joke == null) {
            return null;
        }
        joke.setLang(jokeRequestDTO.getLang());
        joke.setType(jokeRequestDTO.getType());
        joke.setSetup(jokeRequestDTO.getSetup());
        joke.setPunchline(jokeRequestDTO.getPunchline());
        cache.put(JOKE_KEY_PREFIX + id, joke);
        return JokeMapper.toDTO(jokeRepository.save(joke));
    }

    @Override
    public JokeResponseDTO addAuthor(Long jokeId, Long authorId) {
        Joke joke = jokeRepository.findJokeById(jokeId);
        Author author = authorService.getRawAuthorById(authorId);
        if (joke == null || author == null) {
            return null;
        }
        joke.setAuthor(author);
        cache.put(JOKE_KEY_PREFIX + jokeId, joke);
        cache.put(AUTHOR_KEY_PREFIX + authorId, author);
        return JokeMapper.toDTO(jokeRepository.save(joke));
    }

    @Override
    public JokeResponseDTO removeAuthor(Long id) {
        Joke joke = jokeRepository.findJokeById(id);
        if (joke == null || joke.getAuthor() == null) {
            return null;
        }
        joke.setAuthor(null);
        cache.put(JOKE_KEY_PREFIX + id, joke);
        return JokeMapper.toDTO(jokeRepository.save(joke));
    }

    @Override
    public void deleteJoke(Long id) {
        Joke joke = jokeRepository.findJokeById(id);
        if (joke != null) {
            if(joke.getJokeCollections()!=null){
                List<JokeCollection> collections= joke.getJokeCollections().stream().toList();
                for (JokeCollection collection: collections){
                    collection.getJokes().remove(joke);
                }
            }
            if(joke.getAuthor()!=null){
                joke.getAuthor().getJokes().remove(joke);
            }
            jokeRepository.delete(joke);
            cache.remove(JOKE_KEY_PREFIX + id);
        }
    }

    @Override
    public List<JokeResponseDTO> searchAllByAuthorNation(String nation) {
        List<Joke> jokeList = jokeRepository.searchAllByAuthorNation(nation);
        List<JokeResponseDTO> jokeResponseDTOList = new ArrayList<>();
        for (Joke joke : jokeList) {
            jokeResponseDTOList.add(JokeMapper.toDTO(joke));
            cache.put(JOKE_KEY_PREFIX + joke.getId(), joke);
        }
        return jokeResponseDTOList;
    }
}
