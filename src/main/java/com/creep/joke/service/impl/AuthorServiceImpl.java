package com.creep.joke.service.impl;

import com.creep.joke.cache.InMemoryMap;
import com.creep.joke.mapper.AuthorMapper;
import com.creep.joke.model.Author;
import com.creep.joke.model.Joke;
import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.AuthorRequestDTO;
import com.creep.joke.model.dto.AuthorResponseDTO;
import com.creep.joke.repository.AuthorRepository;
import com.creep.joke.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final InMemoryMap cache;
    private static final String AUTHOR_KEY_PREFIX = "author id ";
    private static final String JOKE_COLLECTION_KEY_PREFIX = "jokeCollection id ";
    private static final String JOKE_KEY_PREFIX = "joke id ";

    public AuthorServiceImpl(InMemoryMap cache, AuthorRepository authorRepository){
        this.cache = cache;
        this.authorRepository = authorRepository;
    }


    @Override
    public AuthorResponseDTO postAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = AuthorMapper.toEntity(authorRequestDTO);
        return AuthorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
        List<AuthorResponseDTO> authorResponseDTOList = new ArrayList<>();
        for(Author author : authorList){
            authorResponseDTOList.add(AuthorMapper.toDTO(author));
        }
        return authorResponseDTOList;
    }

    @Override
    public Author getRawAuthorById(Long id) {
        return authorRepository.findAuthorById(id);
    }
    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        if(cache.containsKey(AUTHOR_KEY_PREFIX + id)){
            return AuthorMapper.toDTO((Author) cache.get(AUTHOR_KEY_PREFIX + id));
        }
        Author author = authorRepository.findAuthorById(id);
        cache.put(AUTHOR_KEY_PREFIX + id, author);
        return AuthorMapper.toDTO(author);
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        Author author = authorRepository.findAuthorById(id);
        if(author == null) {
            return null;
        }
        author.setFirstName(authorRequestDTO.getFirstName());
        author.setLastName(authorRequestDTO.getLastName());
        author.setNation(authorRequestDTO.getNation());
        cache.put(AUTHOR_KEY_PREFIX + id, author);
        return AuthorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public void deleteAuthorById(Long id) {
        Author author = authorRepository.findAuthorById(id);
        if (author != null){
            if(author.getJokes() != null) {
                List<Joke> jokes = author.getJokes().stream().toList();
                for (Joke joke: jokes) {
                    joke.setAuthor(null);
                }
            }
            if(author.getJokeCollections() != null) {
                List<JokeCollection> jokeCollections = author.getJokeCollections().stream().toList();
                for (JokeCollection jokeCollection: jokeCollections) {
                    jokeCollection.setAuthor(null);
                }
            }
        cache.remove(AUTHOR_KEY_PREFIX + id);
        authorRepository.delete(author);
        }
    }
}
