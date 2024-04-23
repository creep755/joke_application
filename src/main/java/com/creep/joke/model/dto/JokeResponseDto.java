package com.creep.joke.model.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Joke response dto. */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeResponseDto {
  private Long id;
  private String lang;
  private String type;
  private AuthorResponseDto author;
  private Set<Long> jokeCollectionsId;
  private String setup;
  private String punchline;
}
