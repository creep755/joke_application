package com.creep.joke.service.impl;

import com.creep.joke.cache.InMemoryMap;
import com.creep.joke.mapper.JokeCollectionMapper;
import com.creep.joke.model.Author;
import com.creep.joke.model.Joke;
import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.JokeCollectionRequestDTO;
import com.creep.joke.model.dto.JokeCollectionResponseDTO;
import com.creep.joke.repository.JokeCollectionRepository;
import com.creep.joke.service.AuthorService;
import com.creep.joke.service.JokeCollectionService;
import com.creep.joke.service.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JokeCollectionServiceImpl implements JokeCollectionService {

    private final JokeCollectionRepository jokeCollectionRepository;
    private final JokeService jokeService;
    private final AuthorService authorService;
    private final InMemoryMap cache;
    private static final String JOKE_COLLECTION_KEY_PREFIX = "jokeCollection id ";
    private static final String AUTHOR_KEY_PREFIX = "author id ";
    private static final String JOKE_KEY_PREFIX = "joke id ";
    @Override
    public JokeCollectionResponseDTO postCollection(JokeCollectionRequestDTO jokeCollectionRequestDTO) {
        return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(JokeCollectionMapper.toEntity(jokeCollectionRequestDTO)));
    }

    @Override
    public List<JokeCollectionResponseDTO> getAllCollections() {
        List<JokeCollectionResponseDTO> jokeCollectionResponseDTOList = new ArrayList<>();
        List<JokeCollection> jokeCollectionList = jokeCollectionRepository.findAll();
        for (JokeCollection jokeCollection : jokeCollectionList) {
            jokeCollectionResponseDTOList.add(JokeCollectionMapper.toDTO(jokeCollection));
        }
        return jokeCollectionResponseDTOList;
    }

    @Override
    public JokeCollectionResponseDTO getCollectionById(Long id) {
        if(cache.containsKey(JOKE_COLLECTION_KEY_PREFIX + id)){
            return JokeCollectionMapper.toDTO((JokeCollection) cache.get(JOKE_COLLECTION_KEY_PREFIX + id));
        }
        JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
        cache.put(JOKE_COLLECTION_KEY_PREFIX + id, jokeCollection);
        return JokeCollectionMapper.toDTO(jokeCollection);
    }

    @Override
    public JokeCollectionResponseDTO updateCollection(Long id, JokeCollectionRequestDTO jokeCollectionRequestDTO) {
        JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
        if(jokeCollection == null){
            return null;
        }
        jokeCollection.setTitle(jokeCollectionRequestDTO.getTitle());
        jokeCollection.setDescription(jokeCollectionRequestDTO.getDescription());
        cache.put(JOKE_COLLECTION_KEY_PREFIX + id, jokeCollection);
        return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(jokeCollection));
    }

    @Override
    public JokeCollectionResponseDTO addJoke(Long collectionId, Long jokeId) {
       JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(collectionId);
       Joke joke = jokeService.getRawJokeById(jokeId);
       if (jokeCollection == null || joke == null) {
           return null;
       }
       jokeCollection.getJokes().add(joke);
       cache.put(JOKE_KEY_PREFIX + jokeId, joke);
       cache.put(JOKE_COLLECTION_KEY_PREFIX + collectionId, jokeCollectionRepository.save(jokeCollection));
       return JokeCollectionMapper.toDTO(jokeCollection);
    }

    @Override
    public JokeCollectionResponseDTO removeJoke(Long collectionId, Long jokeId) {
         JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(collectionId);
         Joke joke = jokeService.getRawJokeById(jokeId);
         if(jokeCollection == null || joke == null) {
             return null;
         }
         jokeCollection.getJokes().remove(joke);
         joke.setAuthor(null);
         jokeService.saveJoke(joke);
         cache.put(JOKE_KEY_PREFIX + jokeId, joke);
         cache.put(JOKE_COLLECTION_KEY_PREFIX + collectionId, jokeCollection);
         return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(jokeCollection));
    }

    @Override
    public JokeCollectionResponseDTO addAuthor(Long collectionId, Long authorId) {
        Author author = authorService.getRawAuthorById(authorId);
        JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(collectionId);
        if(jokeCollection == null || author == null){
            return null;
        }
        jokeCollection.setAuthor(author);
        cache.put(JOKE_COLLECTION_KEY_PREFIX + collectionId, jokeCollection);
        cache.put(AUTHOR_KEY_PREFIX + authorId, author);
        return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(jokeCollection));
    }

    @Override
    public JokeCollectionResponseDTO removeAuthor(Long id) {
        JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
        if(jokeCollection == null || jokeCollection.getAuthor() == null){
            return null;
        }
        jokeCollection.setAuthor(null);
        cache.put(JOKE_COLLECTION_KEY_PREFIX + id, jokeCollection);
        return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(jokeCollection));
    }

    @Override
    public JokeCollection save(JokeCollection jokeCollection) {
        if(jokeCollection == null){
            return null;
        }
        return jokeCollectionRepository.save(jokeCollection);
    }


    @Override
    public void deleteCollectionById(Long id) {
        JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
        if(jokeCollection.getJokes() != null){
            List<Joke> jokes = jokeCollection.getJokes().stream().toList();
            for(Joke joke: jokes){
                joke.getJokeCollections().remove(jokeCollection);
            }
        }
        if (jokeCollection.getAuthor() != null){
            jokeCollection.getAuthor().getJokeCollections().remove(jokeCollection);
        }
        cache.remove(JOKE_COLLECTION_KEY_PREFIX + id);
        jokeCollectionRepository.delete(jokeCollection);
    }
}
