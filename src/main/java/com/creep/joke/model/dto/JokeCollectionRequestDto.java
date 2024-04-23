package com.creep.joke.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Joke collection request dto. */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeCollectionRequestDto {
  private String title;
  private String description;
}
