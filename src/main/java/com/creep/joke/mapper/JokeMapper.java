package com.creep.joke.mapper;

import com.creep.joke.model.Joke;
import com.creep.joke.model.dto.JokeRequestDto;
import com.creep.joke.model.dto.JokeResponseDto;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

/** The type Joke mapper. */
@Component
public class JokeMapper {
  private JokeMapper() {}

  /**
   * To dto joke response dto.
   *
   * @param joke the joke
   * @return the joke response dto
   */
  public static JokeResponseDto toDto(Joke joke) {
    if (joke == null) {
      return null;
    }
    JokeResponseDto jokeResponseDto = new JokeResponseDto();
    jokeResponseDto.setId(joke.getId());
    jokeResponseDto.setLang(joke.getLang());
    jokeResponseDto.setType(joke.getType());
    jokeResponseDto.setAuthor(AuthorMapper.toDto(joke.getAuthor()));
    if (joke.getJokeCollections() != null) {
      Set<Long> jokeCollectionsId = new HashSet<>();
      for (int i = 0; i < joke.getJokeCollections().size(); i++) {
        jokeCollectionsId.add(joke.getJokeCollections().stream().toList().get(i).getId());
      }
      jokeResponseDto.setJokeCollectionsId(jokeCollectionsId);
    }

    jokeResponseDto.setSetup(joke.getSetup());
    jokeResponseDto.setPunchline(joke.getPunchline());
    return jokeResponseDto;
  }

  /**
   * To entity joke.
   *
   * @param jokeRequestDto the joke request dto
   * @return the joke
   */
  public static Joke toEntity(JokeRequestDto jokeRequestDto) {
    if (jokeRequestDto == null) {
      return null;
    }
    Joke joke = new Joke();
    joke.setLang(jokeRequestDto.getLang());
    joke.setType(jokeRequestDto.getType());
    joke.setSetup(jokeRequestDto.getSetup());
    joke.setPunchline(jokeRequestDto.getPunchline());
    joke.setJokeCollections(null);
    joke.setAuthor(null);
    return joke;
  }
}
