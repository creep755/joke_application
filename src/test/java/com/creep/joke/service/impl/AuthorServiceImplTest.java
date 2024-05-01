package com.creep.joke.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.creep.joke.cache.InMemoryMap;
import com.creep.joke.mapper.AuthorMapper;
import com.creep.joke.model.Author;
import com.creep.joke.model.dto.AuthorRequestDto;
import com.creep.joke.model.dto.AuthorResponseDto;
import com.creep.joke.repository.AuthorRepository;
import com.creep.joke.service.impl.AuthorServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.postgresql.jdbc2.ArrayAssistant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

  @Mock private AuthorRepository authorRepository;

  @Mock private InMemoryMap cache;

  @InjectMocks private AuthorServiceImpl authorService;

  @BeforeEach
  void setUp() {
    authorService = new AuthorServiceImpl(cache, authorRepository);
  }

  @Test
  void testPostAuthor() {
    // Arrange
    AuthorRequestDto requestDto = new AuthorRequestDto("name1", "sirname1", "nation1");
    Author author = new Author(1L, "name1", "sirname1", "nation1", null, null);
    when(AuthorMapper.toEntity(ArgumentMatchers.any(AuthorRequestDto.class))).thenReturn(author);
    when(authorRepository.save(ArgumentMatchers.any(Author.class))).thenReturn(author);
    when(AuthorMapper.toDto(ArgumentMatchers.any(Author.class))).thenReturn(new AuthorResponseDto());

    // Act
    AuthorResponseDto result = authorService.postAuthor(requestDto);

    // Assert
    assertNotNull(result);
    verify(authorRepository).save(author);
  }

  @Test
  void testGetAllAuthors() {
    // Arrange
    List<Author> authors =
        Arrays.asList(
            new Author(1L, "name1", "sirname1", "nation1", null, null),
            new Author(2L, "name2", "sirname2", "nation2", null, null));
    when(authorRepository.findAll()).thenReturn(authors);

    // Act
    List<AuthorResponseDto> result = authorService.getAllAuthors();

    // Assert
    assertNotNull(result);
  }

  @Test
  void testGetRawAuthorById() {
    // Arrange
    Long id = 1L;
    Author author = new Author(1L,"name1","sirname1","nation1",null,null);
    when(authorRepository.findAuthorById(id)).thenReturn(author);

    // Act
    Author result = authorService.getRawAuthorById(id);

    // Assert
    assertNotNull(result);
    assertEquals(author, result);
  }

  @Test
  void testGetAuthorById() {
    // Arrange
    Long id = 1L;
    Author author = new Author(1L,"name1","sirname1","nation1",null,null);
    when(cache.containsKey(anyString())).thenReturn(true);
    when(cache.get(anyString())).thenReturn(author);

    // Act
    AuthorResponseDto result = authorService.getAuthorById(id);

    // Assert
    assertNotNull(result);
  }

  @Test
  void testUpdateAuthor() {
    // Arrange
    Long id = 1L;
    AuthorRequestDto requestDto = new AuthorRequestDto("name1","sirname1","nation1");
    Author author = new Author(1L,"name1","sirname1","nation1",null,null);
    when(authorRepository.findAuthorById(id)).thenReturn(author);
    when(authorRepository.save(any(Author.class))).thenReturn(author);

    // Act
    AuthorResponseDto result = authorService.updateAuthor(id, requestDto);

    // Assert
    assertNotNull(result);
  }

  @Test
  void testDeleteAuthorById() {
    // Arrange
    Long id = 1L;
    Author author = new Author(1L,"name1","sirname1","nation1",null,null);
    when(authorRepository.findAuthorById(id)).thenReturn(author);
    doNothing().when(authorRepository).delete(any(Author.class));

    // Act
    authorService.deleteAuthorById(id);

    // Assert
    verify(authorRepository).delete(author);
  }
}
