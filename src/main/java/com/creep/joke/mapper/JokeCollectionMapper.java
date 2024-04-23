package com.creep.joke.mapper;

import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.JokeCollectionRequestDto;
import com.creep.joke.model.dto.JokeCollectionResponseDto;
import com.creep.joke.model.dto.JokeResponseDto;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

/** The type Joke collection mapper. */
@Component
public class JokeCollectionMapper {
  private JokeCollectionMapper() {}

  /**
   * To dto joke collection response dto.
   *
   * @param jokeCollection the joke collection
   * @return the joke collection response dto
   */
  public static JokeCollectionResponseDto toDto(JokeCollection jokeCollection) {
    if (jokeCollection == null) {
      return null;
    }
    JokeCollectionResponseDto jokeCollectionResponseDto = new JokeCollectionResponseDto();
    jokeCollectionResponseDto.setId(jokeCollection.getId());
    jokeCollectionResponseDto.setTitle(jokeCollection.getTitle());
    jokeCollectionResponseDto.setDescription(jokeCollection.getDescription());
    if (jokeCollection.getJokes() != null) {
      Set<JokeResponseDto> jokesDto = new HashSet<>();
      for (int i = 0; i < jokeCollection.getJokes().size(); i++) {
        jokesDto.add(JokeMapper.toDto(jokeCollection.getJokes().stream().toList().get(i)));
      }
      jokeCollectionResponseDto.setJokes(jokesDto);
    }
    jokeCollectionResponseDto.setAuthor(AuthorMapper.toDto(jokeCollection.getAuthor()));
    return jokeCollectionResponseDto;
  }

  /**
   * To entity joke collection.
   *
   * @param jokeCollectionRequestDto the joke collection request dto
   * @return the joke collection
   */
  public static JokeCollection toEntity(JokeCollectionRequestDto jokeCollectionRequestDto) {
    if (jokeCollectionRequestDto == null) {
      return null;
    }
    JokeCollection jokeCollection = new JokeCollection();
    jokeCollection.setTitle(jokeCollectionRequestDto.getTitle());
    jokeCollection.setDescription(jokeCollectionRequestDto.getDescription());
    jokeCollection.setJokes(null);
    jokeCollection.setAuthor(null);
    return jokeCollection;
  }
}
