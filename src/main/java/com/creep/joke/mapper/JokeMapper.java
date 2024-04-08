package com.creep.joke.mapper;

import com.creep.joke.model.Joke;
import com.creep.joke.model.dto.JokeRequestDTO;
import com.creep.joke.model.dto.JokeResponseDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JokeMapper {
    private JokeMapper(){
    }

    public static JokeResponseDTO toDTO(Joke joke){
        if (joke == null) {
            return null;
        }
        JokeResponseDTO jokeResponseDTO = new JokeResponseDTO();
        jokeResponseDTO.setId(joke.getId());
        jokeResponseDTO.setLang(joke.getLang());
        jokeResponseDTO.setType(joke.getType());
        jokeResponseDTO.setAuthor(AuthorMapper.toDTO(joke.getAuthor()));
        if (joke.getJokeCollections() != null){
            Set<Long> jokeCollectionsId = new HashSet<>();
            for (int i = 0; i < joke.getJokeCollections().size(); i++) {
                jokeCollectionsId.add(joke.getJokeCollections().stream().toList().get(i).getId());
            }
            jokeResponseDTO.setJokeCollectionsId(jokeCollectionsId);
        }

        jokeResponseDTO.setSetup(joke.getSetup());
        jokeResponseDTO.setPunchline(joke.getPunchline());
        return jokeResponseDTO;
    }

    public static Joke toEntity(JokeRequestDTO jokeRequestDTO){
        if (jokeRequestDTO == null){
            return null;
        }
        Joke joke = new Joke();
        joke.setLang(jokeRequestDTO.getLang());
        joke.setType(jokeRequestDTO.getType());
        joke.setSetup(jokeRequestDTO.getSetup());
        joke.setPunchline(jokeRequestDTO.getPunchline());
        joke.setJokeCollections(null);
        joke.setAuthor(null);
        return joke;
    }
}
