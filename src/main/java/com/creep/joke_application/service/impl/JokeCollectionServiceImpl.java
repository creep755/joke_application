package com.creep.joke_application.service.impl;

import com.creep.joke_application.mapper.JokeCollectionMapper;
import com.creep.joke_application.model.Author;
import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.JokeCollection;
import com.creep.joke_application.model.dto.JokeCollectionRequestDTO;
import com.creep.joke_application.model.dto.JokeCollectionResponseDTO;
import com.creep.joke_application.repository.JokeCollectionRepository;
import com.creep.joke_application.service.AuthorService;
import com.creep.joke_application.service.JokeCollectionService;
import com.creep.joke_application.service.JokeService;
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
        return JokeCollectionMapper.toDTO(jokeCollectionRepository.findJokeCollectionById(id));
    }

    @Override
    public JokeCollectionResponseDTO updateCollection(Long id, JokeCollectionRequestDTO jokeCollectionRequestDTO) {
        JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
        if(jokeCollection == null){
            return null;
        }
        jokeCollection.setTitle(jokeCollectionRequestDTO.getTitle());
        jokeCollection.setDescription(jokeCollectionRequestDTO.getDescription());
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
       return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(jokeCollection));
    }

    @Override
    public JokeCollectionResponseDTO removeJoke(Long collectionId, Long jokeId) {
         JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(collectionId);
         Joke joke = jokeService.getRawJokeById(jokeId);
         if(jokeCollection == null || joke == null) {
             return null;
         }
         jokeCollection.getJokes().remove(joke);
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
        return JokeCollectionMapper.toDTO(jokeCollectionRepository.save(jokeCollection));
    }

    @Override
    public JokeCollectionResponseDTO removeAuthor(Long id) {
        JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
        if(jokeCollection == null || jokeCollection.getAuthor() == null){
            return null;
        }
        jokeCollection.setAuthor(null);
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
        jokeCollectionRepository.deleteById(id);
    }
}
