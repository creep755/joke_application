package com.creep.joke.model.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Joke collection response dto. */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeCollectionResponseDto {
  private Long id;
  private String title;
  private String description;
  private AuthorResponseDto author;
  private Set<JokeResponseDto> jokes;
}
