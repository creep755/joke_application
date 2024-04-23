package com.creep.joke.mapper;

import com.creep.joke.model.Author;
import com.creep.joke.model.dto.AuthorRequestDto;
import com.creep.joke.model.dto.AuthorResponseDto;
import org.springframework.stereotype.Component;

/** The type Author mapper. */
@Component
public class AuthorMapper {
  private AuthorMapper() {}

  /**
   * To dto author response dto.
   *
   * @param author the author
   * @return the author response dto
   */
  public static AuthorResponseDto toDto(Author author) {
    if (author == null) {
      return null;
    }
    AuthorResponseDto authorResponseDto = new AuthorResponseDto();
    authorResponseDto.setId(author.getId());
    authorResponseDto.setFirstName(author.getFirstName());
    authorResponseDto.setLastName(author.getLastName());
    authorResponseDto.setNation(author.getNation());
    return authorResponseDto;
  }

  /**
   * To entity author.
   *
   * @param authorRequestDto the author request dto
   * @return the author
   */
  public static Author toEntity(AuthorRequestDto authorRequestDto) {
    if (authorRequestDto == null) {
      return null;
    }
    Author author = new Author();
    author.setFirstName(authorRequestDto.getFirstName());
    author.setLastName(authorRequestDto.getLastName());
    author.setNation(authorRequestDto.getNation());
    return author;
  }
}
