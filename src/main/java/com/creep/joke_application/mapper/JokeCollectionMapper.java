package com.creep.joke_application.mapper;

import com.creep.joke_application.model.JokeCollection;
import com.creep.joke_application.model.dto.JokeCollectionDTO;

import org.springframework.stereotype.Component;

@Component
public class JokeCollectionMapper {
    private JokeCollectionMapper(){
    }
    public static JokeCollectionDTO toDTO(JokeCollection jokeCollection){
        if(jokeCollection == null) {
            return null;
        }
        JokeCollectionDTO jokeCollectionDTO = new JokeCollectionDTO();
        jokeCollectionDTO.setId(jokeCollection.getId());
        jokeCollectionDTO.setName(jokeCollection.getName());
        jokeCollectionDTO.setDescription(jokeCollection.getDescription());
        jokeCollectionDTO.setJokes(jokeCollection.getJokes());
        jokeCollectionDTO.setAuthor(jokeCollection.getAuthor());
        return jokeCollectionDTO;
    }
    public static JokeCollection toEntity(JokeCollectionDTO jokeCollectionDTO){
        if(jokeCollectionDTO == null) {
            return null;
        }
        JokeCollection jokeCollection = new JokeCollection();
        jokeCollection.setId(jokeCollectionDTO.getId());
        jokeCollection.setName(jokeCollectionDTO.getName());
        jokeCollection.setDescription(jokeCollectionDTO.getDescription());
        jokeCollection.setJokes(jokeCollectionDTO.getJokes());
        return jokeCollection;
    }
}
