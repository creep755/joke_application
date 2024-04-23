package com.creep.joke.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.creep.joke.model.dto.JokeRequestDto;
import com.creep.joke.model.dto.JokeResponseDto;
import com.creep.joke.service.JokeService;
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
class JokeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JokeService jokeService;

    @InjectMocks
    private JokeController jokeController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(jokeController).build();
    }

    @Test
    void testPostJoke() throws Exception {
        JokeRequestDto requestDto = new JokeRequestDto("lang1","type1","setup1","punchline1");
        JokeResponseDto responseDto = new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1");

        when(jokeService.postJoke(ArgumentMatchers.any(JokeRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/jokes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testPostListOfJokes() throws Exception {
        List<JokeRequestDto> requestDtoList = Arrays.asList(new JokeRequestDto("lang1","type1","setup1","punchline1"), new JokeRequestDto("lang2","type2","setup2","punchline2"));
        List<JokeResponseDto> responseDtoList = Arrays.asList(new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1"), new JokeResponseDto(2L,"lang2","type2",null,null,"setup2","punchline2"));

        when(jokeService.postListOfJokes(anyList())).thenReturn(responseDtoList);

        mockMvc.perform(post("/api/v1/jokes/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(responseDtoList.size())));
    }

    @Test
    void testGetAllJokes() throws Exception {
        List<JokeResponseDto> allJokes = Arrays.asList(new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1"), new JokeResponseDto(2L,"lang2","type2",null,null,"setup2","punchline2"));

        when(jokeService.getAllJokes()).thenReturn(allJokes);

        mockMvc.perform(get("/api/v1/jokes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(allJokes.size())));
    }

    @Test
    void testGetJokeById() throws Exception {
        Long jokeId = 1L;
        JokeResponseDto responseDto = new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1");

        when(jokeService.findJokeById(jokeId)).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/jokes/{id}", jokeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testGetRandomJoke() throws Exception {
        JokeResponseDto responseDto = new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1");

        when(jokeService.getRandomJoke()).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/jokes/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testGetRandomJokeByType() throws Exception {
        String type = "general";
        JokeResponseDto responseDto = new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1");

        when(jokeService.getRandomJokeByType(type)).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/jokes/random/{type}", type))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testUpdateJokeById() throws Exception {
        Long jokeId = 1L;
        JokeRequestDto requestDto = new JokeRequestDto("lang1","type1","setup1","punchline1");
        JokeResponseDto responseDto = new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1");

        when(jokeService.updateJokeById(eq(jokeId), ArgumentMatchers.any(JokeRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/jokes/{id}", jokeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testAddAuthor() throws Exception {
        Long jokeId = 1L;
        Long authorId = 10L;
        JokeResponseDto responseDto = new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1");

        when(jokeService.addAuthor(jokeId, authorId)).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/jokes/add-author/{jokeId}", jokeId)
                        .param("authorId", authorId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testRemoveAuthor() throws Exception {
        Long authorId = 10L;
        JokeResponseDto responseDto = new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1");

        when(jokeService.removeAuthor(authorId)).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/jokes/remove-author/{id}", authorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testDeleteJoke() throws Exception {
        Long jokeId = 1L;

        doNothing().when(jokeService).deleteJoke(jokeId);

        mockMvc.perform(delete("/api/v1/jokes/{id}", jokeId))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchJokesByAuthorNation() throws Exception {
        String nation = "USA";
        List<JokeResponseDto> jokesList = Arrays.asList(new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1"), new JokeResponseDto(2L,"lang2","type2",null,null,"setup2","punchline2"));

        when(jokeService.searchAllByAuthorNation(nation)).thenReturn(jokesList);

        mockMvc.perform(get("/api/v1/jokes/search")
                        .param("nation", nation))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(jokesList.size())));
    }
}
