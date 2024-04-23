package com.creep.joke.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Error response model. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseModel {
  /** The Message. */
  String message;

  /** The Status code. */
  Integer statusCode;
}
