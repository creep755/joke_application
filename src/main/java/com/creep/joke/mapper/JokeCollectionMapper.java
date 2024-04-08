package com.creep.joke.mapper;

import com.creep.joke.model.JokeCollection;
import com.creep.joke.model.dto.JokeCollectionRequestDTO;
import com.creep.joke.model.dto.JokeCollectionResponseDTO;

import com.creep.joke.model.dto.JokeResponseDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JokeCollectionMapper {
    private JokeCollectionMapper(){
    }

    public static JokeCollectionResponseDTO toDTO(JokeCollection jokeCollection){
        if(jokeCollection == null) {
            return null;
        }
        JokeCollectionResponseDTO jokeCollectionResponseDTO = new JokeCollectionResponseDTO();
        jokeCollectionResponseDTO.setId(jokeCollection.getId());
        jokeCollectionResponseDTO.setTitle(jokeCollection.getTitle());
        jokeCollectionResponseDTO.setDescription(jokeCollection.getDescription());
        if (jokeCollection.getJokes()!=null){
            Set<JokeResponseDTO> jokesDTO = new HashSet<>();
            for (int i = 0; i < jokeCollection.getJokes().size(); i++) {
                jokesDTO.add(JokeMapper.toDTO(jokeCollection.getJokes().stream().toList().get(i)));
            }
            jokeCollectionResponseDTO.setJokes(jokesDTO);
        }
        jokeCollectionResponseDTO.setAuthor(AuthorMapper.toDTO(jokeCollection.getAuthor()));
        return jokeCollectionResponseDTO;
    }

    public static JokeCollection toEntity(JokeCollectionRequestDTO jokeCollectionRequestDTO){
        if (jokeCollectionRequestDTO == null){
            return null;
        }
        JokeCollection jokeCollection = new JokeCollection();
        jokeCollection.setTitle(jokeCollectionRequestDTO.getTitle());
        jokeCollection.setDescription(jokeCollectionRequestDTO.getDescription());
        jokeCollection.setJokes(null);
        jokeCollection.setAuthor(null);
        return jokeCollection;
    }
}
