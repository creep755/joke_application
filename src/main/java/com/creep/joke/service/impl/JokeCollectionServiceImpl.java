package com.creep.joke.service.impl;

import com.creep.joke.cache.InMemoryMap;
import com.creep.joke.mapper.JokeCollectionMapper;
import com.creep.joke.model.Author;
import com.creep.joke.model.Joke;
import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.JokeCollectionRequestDto;
import com.creep.joke.model.dto.JokeCollectionResponseDto;
import com.creep.joke.repository.JokeCollectionRepository;
import com.creep.joke.service.AuthorService;
import com.creep.joke.service.JokeCollectionService;
import com.creep.joke.service.JokeService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** The type Joke collection service. */
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
  public JokeCollectionResponseDto postCollection(
      JokeCollectionRequestDto jokeCollectionRequestDto) {
    return JokeCollectionMapper.toDto(
        jokeCollectionRepository.save(JokeCollectionMapper.toEntity(jokeCollectionRequestDto)));
  }

  @Override
  public List<JokeCollectionResponseDto> getAllCollections() {
    List<JokeCollectionResponseDto> jokeCollectionResponseDtoList = new ArrayList<>();
    List<JokeCollection> jokeCollectionList = jokeCollectionRepository.findAll();
    for (JokeCollection jokeCollection : jokeCollectionList) {
      jokeCollectionResponseDtoList.add(JokeCollectionMapper.toDto(jokeCollection));
    }
    return jokeCollectionResponseDtoList;
  }

  @Override
  public JokeCollectionResponseDto getCollectionById(Long id) {
    if (cache.containsKey(JOKE_COLLECTION_KEY_PREFIX + id)) {
      return JokeCollectionMapper.toDto(
          (JokeCollection) cache.get(JOKE_COLLECTION_KEY_PREFIX + id));
    }
    JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
    cache.put(JOKE_COLLECTION_KEY_PREFIX + id, jokeCollection);
    return JokeCollectionMapper.toDto(jokeCollection);
  }

  @Override
  public JokeCollectionResponseDto updateCollection(
      Long id, JokeCollectionRequestDto jokeCollectionRequestDto) {
    JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
    if (jokeCollection == null) {
      return null;
    }
    jokeCollection.setTitle(jokeCollectionRequestDto.getTitle());
    jokeCollection.setDescription(jokeCollectionRequestDto.getDescription());
    cache.put(JOKE_COLLECTION_KEY_PREFIX + id, jokeCollection);
    return JokeCollectionMapper.toDto(jokeCollectionRepository.save(jokeCollection));
  }

  @Override
  public JokeCollectionResponseDto addJoke(Long collectionId, Long jokeId) {
    JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(collectionId);
    Joke joke = jokeService.getRawJokeById(jokeId);
    if (jokeCollection == null || joke == null) {
      return null;
    }
    jokeCollection.getJokes().add(joke);
    cache.put(JOKE_KEY_PREFIX + jokeId, joke);
    cache.put(
        JOKE_COLLECTION_KEY_PREFIX + collectionId, jokeCollectionRepository.save(jokeCollection));
    return JokeCollectionMapper.toDto(jokeCollection);
  }

  @Override
  public JokeCollectionResponseDto removeJoke(Long collectionId, Long jokeId) {
    JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(collectionId);
    Joke joke = jokeService.getRawJokeById(jokeId);
    if (jokeCollection == null || joke == null) {
      return null;
    }
    jokeCollection.getJokes().remove(joke);
    joke.setAuthor(null);
    jokeService.saveJoke(joke);
    cache.put(JOKE_KEY_PREFIX + jokeId, joke);
    cache.put(JOKE_COLLECTION_KEY_PREFIX + collectionId, jokeCollection);
    return JokeCollectionMapper.toDto(jokeCollectionRepository.save(jokeCollection));
  }

  @Override
  public JokeCollectionResponseDto addAuthor(Long collectionId, Long authorId) {
    Author author = authorService.getRawAuthorById(authorId);
    JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(collectionId);
    if (jokeCollection == null || author == null) {
      return null;
    }
    jokeCollection.setAuthor(author);
    cache.put(JOKE_COLLECTION_KEY_PREFIX + collectionId, jokeCollection);
    cache.put(AUTHOR_KEY_PREFIX + authorId, author);
    return JokeCollectionMapper.toDto(jokeCollectionRepository.save(jokeCollection));
  }

  @Override
  public JokeCollectionResponseDto removeAuthor(Long id) {
    JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
    if (jokeCollection == null || jokeCollection.getAuthor() == null) {
      return null;
    }
    jokeCollection.setAuthor(null);
    cache.put(JOKE_COLLECTION_KEY_PREFIX + id, jokeCollection);
    return JokeCollectionMapper.toDto(jokeCollectionRepository.save(jokeCollection));
  }

  @Override
  public JokeCollection save(JokeCollection jokeCollection) {
    if (jokeCollection == null) {
      return null;
    }
    return jokeCollectionRepository.save(jokeCollection);
  }

  @Override
  public void deleteCollectionById(Long id) {
    JokeCollection jokeCollection = jokeCollectionRepository.findJokeCollectionById(id);
    if (jokeCollection.getJokes() != null) {
      List<Joke> jokes = jokeCollection.getJokes().stream().toList();
      for (Joke joke : jokes) {
        joke.getJokeCollections().remove(jokeCollection);
      }
    }
    if (jokeCollection.getAuthor() != null) {
      jokeCollection.getAuthor().getJokeCollections().remove(jokeCollection);
    }
    cache.remove(JOKE_COLLECTION_KEY_PREFIX + id);
    jokeCollectionRepository.delete(jokeCollection);
  }
}
