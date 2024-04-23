package com.creep.joke.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Joke request dto. */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeRequestDto {
  private String lang;
  private String type;
  private String setup;
  private String punchline;
}
