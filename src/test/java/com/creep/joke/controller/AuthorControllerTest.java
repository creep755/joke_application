package com.creep.joke.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.creep.joke.service.AuthorService;
import com.creep.joke.model.dto.AuthorRequestDto;
import com.creep.joke.model.dto.AuthorResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
class AuthorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(authorController).build();
    }

    @Test
    void testPostAuthor() throws Exception {
        AuthorRequestDto requestDto = new AuthorRequestDto("name1","sirname1","nation1");
        AuthorResponseDto responseDto = new AuthorResponseDto(1L,"name1","sirname1","nation1");

        when(authorService.postAuthor(ArgumentMatchers.any(AuthorRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testGetAllAuthors() throws Exception {
        List<AuthorResponseDto> authorsList = Arrays.asList(
                new AuthorResponseDto(1L,"name1","sirname1","nation1"),
                new AuthorResponseDto(2L, "name2","sirname2","nation2")
        );

        when(authorService.getAllAuthors()).thenReturn(authorsList);

        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(authorsList.size())));
    }

    @Test
    void testGetAuthorById() throws Exception {
        Long authorId = 1L;
        AuthorResponseDto responseDto = new AuthorResponseDto(1L, "name1","sirname1","nation1");

        when(authorService.getAuthorById(authorId)).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/authors/{id}", authorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testUpdateAuthor() throws Exception {
        Long authorId = 1L;
        AuthorRequestDto requestDto = new AuthorRequestDto("name1","sirname1","nation1");
        AuthorResponseDto responseDto = new AuthorResponseDto(1L,"name1","sirname1","nation1");

        when(authorService.updateAuthor(eq(authorId), ArgumentMatchers.any(AuthorRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/authors/{id}", authorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testDeleteAuthorById() throws Exception {
        Long authorId = 1L;

        doNothing().when(authorService).deleteAuthorById(authorId);

        mockMvc.perform(delete("/api/v1/authors/{id}", authorId))
                .andExpect(status().isOk());
    }
}
