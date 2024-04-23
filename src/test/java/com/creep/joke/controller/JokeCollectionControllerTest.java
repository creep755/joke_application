package com.creep.joke.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.creep.joke.model.dto.JokeCollectionRequestDto;
import com.creep.joke.model.dto.JokeCollectionResponseDto;
import com.creep.joke.service.JokeCollectionService;
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
class JokeCollectionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JokeCollectionService jokeCollectionService;

    @InjectMocks
    private JokeCollectionController jokeCollectionController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(jokeCollectionController).build();
    }

    @Test
    void testPostCollection() throws Exception {
        JokeCollectionRequestDto requestDto = new JokeCollectionRequestDto("title1","description1");
        JokeCollectionResponseDto responseDto = new JokeCollectionResponseDto(1L,"title1","description1",null,null);

        when(jokeCollectionService.postCollection(ArgumentMatchers.any(JokeCollectionRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/collections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testGetAllJokeCollections() throws Exception {
        List<JokeCollectionResponseDto> collectionsList = Arrays.asList(
                new JokeCollectionResponseDto(1L,"title1","description1",null,null),
                new JokeCollectionResponseDto(2L,"title2","description2",null,null)
        );

        when(jokeCollectionService.getAllCollections()).thenReturn(collectionsList);

        mockMvc.perform(get("/api/v1/collections"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(collectionsList.size())));
    }

    @Test
    void testGetJokeCollectionById() throws Exception {
        Long collectionId = 1L;
        JokeCollectionResponseDto responseDto = new JokeCollectionResponseDto(1L,"title1","description1",null,null);

        when(jokeCollectionService.getCollectionById(collectionId)).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/collections/{id}", collectionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testUpdateJokeCollection() throws Exception {
        Long collectionId = 1L;
        JokeCollectionRequestDto requestDto = new JokeCollectionRequestDto("title1","description1");
        JokeCollectionResponseDto responseDto = new JokeCollectionResponseDto(1L,"title1","description1",null,null);

        when(jokeCollectionService.updateCollection(eq(collectionId), ArgumentMatchers.any(JokeCollectionRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/collections/{id}", collectionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testAddJoke() throws Exception {
        Long collectionId = 1L;
        Long jokeId = 10L;
        JokeCollectionResponseDto responseDto = new JokeCollectionResponseDto(1L,"title1","description1",null,null);

        when(jokeCollectionService.addJoke(collectionId, jokeId)).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/collections/add-joke/{collectionId}", collectionId)
                        .param("jokeId", jokeId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testRemoveJoke() throws Exception {
        Long collectionId = 1L;
        Long jokeId = 10L;
        JokeCollectionResponseDto responseDto = new JokeCollectionResponseDto(1L,"title1","description1",null,null);

        when(jokeCollectionService.removeJoke(collectionId, jokeId)).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/collections/remove-joke/{collectionId}", collectionId)
                        .param("jokeId", jokeId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testAddAuthor() throws Exception {
        Long collectionId = 1L;
        Long authorId = 5L;
        JokeCollectionResponseDto responseDto = new JokeCollectionResponseDto(1L,"title1","description1",null,null);

        when(jokeCollectionService.addAuthor(collectionId, authorId)).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/collections/add-author/{collectionId}", collectionId)
                        .param("authorId", authorId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testRemoveAuthor() throws Exception {
        Long authorId = 5L;
        JokeCollectionResponseDto responseDto = new JokeCollectionResponseDto(1L,"title1","description1",null,null);

        when(jokeCollectionService.removeAuthor(authorId)).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/collections/remove-author/{id}", authorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testDeleteJokeCollectionById() throws Exception {
        Long collectionId = 1L;

        doNothing().when(jokeCollectionService).deleteCollectionById(collectionId);

        mockMvc.perform(delete("/api/v1/collections/{id}", collectionId))
                .andExpect(status().isOk());
    }

}
