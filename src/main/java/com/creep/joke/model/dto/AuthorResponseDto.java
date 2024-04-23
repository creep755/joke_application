package com.creep.joke.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Author response dto. */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String nation;
}
