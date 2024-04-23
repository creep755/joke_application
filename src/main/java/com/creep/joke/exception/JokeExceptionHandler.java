package com.creep.joke.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** The type Joke exception handler. */
@ControllerAdvice
@Slf4j
public class JokeExceptionHandler extends ResponseEntityExceptionHandler {

  @Nullable
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      @Nullable Object body,
      HttpHeaders headers,
      HttpStatusCode statusCode,
      WebRequest request) {
    if (statusCode.equals(HttpStatus.NOT_FOUND)) {
      ErrorResponseModel errorResponse =
          new ErrorResponseModel(
              "Resource Not Found Error: " + ex.getMessage() + " Are you lost, baby girl?😘 ;)",
              statusCode.value());
      return new ResponseEntity<>(errorResponse, headers, statusCode);
    } else if (statusCode.equals(HttpStatus.BAD_REQUEST)) {
      ErrorResponseModel errorResponse =
          new ErrorResponseModel(
              "Bad Request Error: "
                  + ex.getMessage()
                  + " Your behavior is obscene! You better fix it, baby!😈 :)",
              statusCode.value());
      return new ResponseEntity<>(errorResponse, headers, statusCode);
    } else if (statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
      ErrorResponseModel errorResponse =
          new ErrorResponseModel(
              "Internal Server Error: "
                  + ex.getMessage()
                  + " Sorry, I'm just a piece of metal🤕 :(",
              statusCode.value());
      return new ResponseEntity<>(errorResponse, headers, statusCode);
    }
    return new ResponseEntity<>(body, headers, statusCode);
  }

  /**
   * Handle internal server error exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseModel> handleInternalServerErrorException(Exception ex) {
    ErrorResponseModel errorResponse =
        new ErrorResponseModel(
            "Internal Server Error: " + ex.getMessage() + " Sorry, I'm just a piece of metal🤕 :(",
            HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
