package com.creep.joke.service.impl;

import com.creep.joke.cache.InMemoryMap;
import com.creep.joke.mapper.AuthorMapper;
import com.creep.joke.model.Author;
import com.creep.joke.model.Joke;
import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.AuthorRequestDto;
import com.creep.joke.model.dto.AuthorResponseDto;
import com.creep.joke.repository.AuthorRepository;
import com.creep.joke.service.AuthorService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/** The type Author service. */
@Service
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private final InMemoryMap cache;
  private static final String AUTHOR_KEY_PREFIX = "author id ";

  /**
   * Instantiates a new Author service.
   *
   * @param cache the cache
   * @param authorRepository the author repository
   */
  public AuthorServiceImpl(InMemoryMap cache, AuthorRepository authorRepository) {
    this.cache = cache;
    this.authorRepository = authorRepository;
  }

  @Override
  public AuthorResponseDto postAuthor(AuthorRequestDto authorRequestDto) {
    Author author = AuthorMapper.toEntity(authorRequestDto);
    return AuthorMapper.toDto(authorRepository.save(author));
  }

  @Override
  public List<AuthorResponseDto> getAllAuthors() {
    List<Author> authorList = authorRepository.findAll();
    List<AuthorResponseDto> authorResponseDtoList = new ArrayList<>();
    for (Author author : authorList) {
      authorResponseDtoList.add(AuthorMapper.toDto(author));
    }
    return authorResponseDtoList;
  }

  @Override
  public Author getRawAuthorById(Long id) {
    return authorRepository.findAuthorById(id);
  }

  @Override
  public List<AuthorResponseDto> getAuthorByName(String name) {
    return authorRepository.findAll().stream()
        .filter(author -> Objects.equals(author.getFirstName(), name))
        .map(AuthorMapper::toDto)
        .toList();
  }

  @Override
  public AuthorResponseDto getAuthorById(Long id) {
    if (cache.containsKey(AUTHOR_KEY_PREFIX + id)) {
      return AuthorMapper.toDto((Author) cache.get(AUTHOR_KEY_PREFIX + id));
    }
    Author author = authorRepository.findAuthorById(id);
    cache.put(AUTHOR_KEY_PREFIX + id, author);
    return AuthorMapper.toDto(author);
  }

  @Override
  public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto authorRequestDto) {
    Author author = authorRepository.findAuthorById(id);
    if (author == null) {
      return null;
    }
    author.setFirstName(authorRequestDto.getFirstName());
    author.setLastName(authorRequestDto.getLastName());
    author.setNation(authorRequestDto.getNation());
    cache.put(AUTHOR_KEY_PREFIX + id, author);
    return AuthorMapper.toDto(authorRepository.save(author));
  }

  @Override
  public void deleteAuthorById(Long id) {
    Author author = authorRepository.findAuthorById(id);
    if (author != null) {
      if (author.getJokes() != null) {
        List<Joke> jokes = author.getJokes().stream().toList();
        for (Joke joke : jokes) {
          joke.setAuthor(null);
        }
      }
      if (author.getJokeCollections() != null) {
        List<JokeCollection> jokeCollections = author.getJokeCollections().stream().toList();
        for (JokeCollection jokeCollection : jokeCollections) {
          jokeCollection.setAuthor(null);
        }
      }
      cache.remove(AUTHOR_KEY_PREFIX + id);
      authorRepository.delete(author);
    }
  }
}
