package com.creep.joke.service.impl;

import com.creep.joke.cache.InMemoryMap;
import com.creep.joke.mapper.JokeMapper;
import com.creep.joke.model.Author;
import com.creep.joke.model.Joke;
import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.JokeRequestDto;
import com.creep.joke.model.dto.JokeResponseDto;
import com.creep.joke.repository.JokeRepository;
import com.creep.joke.service.AuthorService;
import com.creep.joke.service.JokeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** The type Joke service. */
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
  public JokeResponseDto postJoke(JokeRequestDto jokeRequestDto) {
    Joke joke = JokeMapper.toEntity(jokeRequestDto);
    if (joke == null) {
      return null;
    }
    return JokeMapper.toDto(jokeRepository.save(joke));
  }

  @Override
  public List<JokeResponseDto> postListOfJokes(List<JokeRequestDto> jokeRequestDtoList) {
    return jokeRequestDtoList.stream().map(this::postJoke).toList();
  }

  @Override
  public Joke saveJoke(Joke joke) {
    return jokeRepository.save(joke);
  }

  @Override
  public List<JokeResponseDto> getAllJokes() {
    List<Joke> jokeList = jokeRepository.findAll();
    List<JokeResponseDto> jokeResponseDtoList = new ArrayList<>();
    for (Joke joke : jokeList) {
      jokeResponseDtoList.add(JokeMapper.toDto(joke));
    }
    return jokeResponseDtoList;
  }

  @Override
  public JokeResponseDto findJokeById(Long id) {
    if (cache.containsKey(JOKE_KEY_PREFIX + id)) {
      return JokeMapper.toDto((Joke) cache.get(JOKE_KEY_PREFIX + id));
    }
    Joke joke = jokeRepository.findJokeById(id);
    cache.put(JOKE_KEY_PREFIX + id, joke);
    return JokeMapper.toDto(joke);
  }

  @Override
  public JokeResponseDto getRandomJoke() {
    var jokeList = jokeRepository.findAll();

    return JokeMapper.toDto(jokeList.get(rand.nextInt(0, jokeList.size())));
  }

  @Override
  public JokeResponseDto getRandomJokeByType(String type) {
    var jokeList = jokeRepository.findAllByType(type);
    return JokeMapper.toDto(jokeList.get(rand.nextInt(0, jokeList.size())));
  }

  @Override
  public Joke getRawJokeById(Long id) {
    return jokeRepository.findJokeById(id);
  }

  @Override
  public JokeResponseDto updateJokeById(Long id, JokeRequestDto jokeRequestDto) {
    Joke joke = jokeRepository.findJokeById(id);
    if (joke == null) {
      return null;
    }
    joke.setLang(jokeRequestDto.getLang());
    joke.setType(jokeRequestDto.getType());
    joke.setSetup(jokeRequestDto.getSetup());
    joke.setPunchline(jokeRequestDto.getPunchline());
    cache.put(JOKE_KEY_PREFIX + id, joke);
    return JokeMapper.toDto(jokeRepository.save(joke));
  }

  @Override
  public JokeResponseDto addAuthor(Long jokeId, Long authorId) {
    Joke joke = jokeRepository.findJokeById(jokeId);
    Author author = authorService.getRawAuthorById(authorId);
    if (joke == null) {
      return null;
    }
    joke.setAuthor(author);
    cache.put(JOKE_KEY_PREFIX + jokeId, joke);
    cache.put(AUTHOR_KEY_PREFIX + authorId, author);
    return JokeMapper.toDto(jokeRepository.save(joke));
  }

  @Override
  public JokeResponseDto removeAuthor(Long id) {
    Joke joke = jokeRepository.findJokeById(id);
    if (joke == null) {
      return null;
    }
    joke.setAuthor(null);
    cache.put(JOKE_KEY_PREFIX + id, joke);
    return JokeMapper.toDto(jokeRepository.save(joke));
  }

  @Override
  public void deleteJoke(Long id) {
    Joke joke = jokeRepository.findJokeById(id);
    if (joke != null) {
      if (joke.getJokeCollections() != null) {
        List<JokeCollection> collections = joke.getJokeCollections().stream().toList();
        for (JokeCollection collection : collections) {
          collection.getJokes().remove(joke);
        }
      }
      if (joke.getAuthor() != null) {
        joke.getAuthor().getJokes().remove(joke);
      }
      jokeRepository.delete(joke);
      cache.remove(JOKE_KEY_PREFIX + id);
    }
  }

  @Override
  public List<JokeResponseDto> searchAllByAuthorNation(String nation) {
    List<Joke> jokeList = jokeRepository.searchAllByAuthorNation(nation);
    List<JokeResponseDto> jokeResponseDtoList = new ArrayList<>();
    for (Joke joke : jokeList) {
      jokeResponseDtoList.add(JokeMapper.toDto(joke));
      cache.put(JOKE_KEY_PREFIX + joke.getId(), joke);
    }
    return jokeResponseDtoList;
  }
}
