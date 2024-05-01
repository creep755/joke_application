package com.creep.joke.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;


import com.creep.joke.model.Joke;
import com.creep.joke.model.dto.JokeRequestDto;
import com.creep.joke.model.dto.JokeResponseDto;
import com.creep.joke.repository.JokeRepository;
import com.creep.joke.service.AuthorService;
import com.creep.joke.cache.InMemoryMap;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
class JokeServiceImplTest {

    @Mock
    private JokeRepository jokeRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private InMemoryMap cache;

    @Mock
    private Random rand;

    @InjectMocks
    private JokeServiceImpl jokeService;

    @BeforeEach
    void setUp() {
        jokeService = new JokeServiceImpl(jokeRepository, rand, authorService, cache);
    }


    @Test
    void testPostListOfJokes() {

        List<JokeRequestDto> jokeRequestDtoList = List.of(new JokeRequestDto("lang1","type1","setup1","punchline1"), new JokeRequestDto("lang2","type2","setup2","punchline2"));
        List<JokeResponseDto> expectedResponseList = List.of(new JokeResponseDto(1L,"lang1","type1",null,null,"setup1","punchline1"), new JokeResponseDto(2L,"lang2","type2",null,null,"setup2","punchline2"));


        List<JokeResponseDto> actualResponseList = jokeService.postListOfJokes(jokeRequestDtoList);

        assertNotNull(actualResponseList);
        assertEquals(expectedResponseList.size(), actualResponseList.size());
    }

    @Test
    void testGetAllJokes() {
        List<Joke> jokes = List.of(new Joke(1L,"lang1","type1",null,null,"setup1","punchline1"), new Joke(2L,"lang2","type2",null,null,"setup2","punchline2"));
        when(jokeRepository.findAll()).thenReturn(jokes);

        List<JokeResponseDto> result = jokeService.getAllJokes();

        assertNotNull(result);
        assertEquals(jokes.size(), result.size());
    }

    @Test
    void testFindJokeById() {
        Long id = 1L;
        Joke joke = new Joke(1L,"lang1","type1",null,null,"setup1","punchline1");
        when(cache.containsKey(anyString())).thenReturn(true);
        when(cache.get(anyString())).thenReturn(joke);

        JokeResponseDto result = jokeService.findJokeById(id);

        assertNotNull(result);
    }

    @Test
    void testGetRandomJoke() {
        List<Joke> jokes = List.of(new Joke(1L,"lang1","type1",null,null,"setup1","punchline1"), new Joke(2L,"lang2","type2",null,null,"setup2","punchline2"));
        when(jokeRepository.findAll()).thenReturn(jokes);
        when(rand.nextInt(anyInt(), anyInt())).thenReturn(0);

        JokeResponseDto result = jokeService.getRandomJoke();

        assertNotNull(result);
    }

    @Test
    void testGetRandomJokeByType() {
        String type = "general";
        List<Joke> jokes = List.of(new Joke(1L,"lang1","type1",null,null,"setup1","punchline1"), new Joke(2L,"lang2","type2",null,null,"setup2","punchline2"));
        when(jokeRepository.findAllByType(type)).thenReturn(jokes);
        when(rand.nextInt(anyInt(), anyInt())).thenReturn(0);

        JokeResponseDto result = jokeService.getRandomJokeByType(type);

        assertNotNull(result);
    }


    @Test
    void testUpdateJokeById() {
        Long id = 1L;
        JokeRequestDto jokeRequestDto = new JokeRequestDto("lang1","type1","setup1","punchline1");
        Joke joke = new Joke(1L,"lang1","type1",null,null,"setup1","punchline1");
        when(jokeRepository.findJokeById(id)).thenReturn(joke);
        when(jokeRepository.save(any(Joke.class))).thenReturn(joke);

        JokeResponseDto result = jokeService.updateJokeById(id, jokeRequestDto);

        assertNotNull(result);
    }

    @Test
    void testAddAuthor() {

        Long jokeId = 1L;
        Long authorId = 2L;
        Joke joke = new Joke(1L,"lang1","type1",null,null,"setup1","punchline1");
        when(jokeRepository.findJokeById(jokeId)).thenReturn(joke);
        when(jokeRepository.save(any(Joke.class))).thenReturn(joke);


        JokeResponseDto result = jokeService.addAuthor(jokeId, authorId);

        assertNotNull(result);
    }

    @Test
    void testRemoveAuthor() {
        Long id = 1L;
        Joke joke = new Joke(1L,"lang1","type1",null,null,"setup1","punchline1");
        when(jokeRepository.findJokeById(id)).thenReturn(joke);
        when(jokeRepository.save(any(Joke.class))).thenReturn(joke);

        JokeResponseDto result = jokeService.removeAuthor(id);

        assertNotNull(result);
    }

    @Test
    void testDeleteJoke() {
        // Arrange
        Long id = 1L;
        Joke joke = new Joke(1L,"lang1","type1",null,null,"setup1","punchline1");
        when(jokeRepository.findJokeById(id)).thenReturn(joke);
        doNothing().when(jokeRepository).delete(any(Joke.class));

        jokeService.deleteJoke(id);

        verify(jokeRepository).delete(joke);
    }

    @Test
    void testSearchAllByAuthorNation() {
        String nation = "USA";
        List<Joke> jokes = List.of(new Joke(1L,"lang1","type1",null,null,"setup1","punchline1"), new Joke(2L,"lang2","type2",null,null,"setup2","punchline2"));
        when(jokeRepository.searchAllByAuthorNation(nation)).thenReturn(jokes);

        List<JokeResponseDto> result = jokeService.searchAllByAuthorNation(nation);

        assertNotNull(result);
        assertEquals(jokes.size(), result.size());
    }
}
