package com.creep.joke_application.mapper;

import com.creep.joke_application.model.JokeCollection;
import com.creep.joke_application.model.dto.JokeCollectionDTO;

import com.creep.joke_application.model.dto.JokeDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JokeCollectionMapper {
    private JokeCollectionMapper(){
    }
    //todo
    // добавить мапперы отдельно для .Response и .Request
    public static JokeCollectionDTO toDTO(JokeCollection jokeCollection){
        if(jokeCollection == null) {
            return null;
        }
        JokeCollectionDTO jokeCollectionDTO = new JokeCollectionDTO();
        jokeCollectionDTO.setId(jokeCollection.getId());
        jokeCollectionDTO.setName(jokeCollection.getName());
        jokeCollectionDTO.setDescription(jokeCollection.getDescription());
        if (jokeCollection.getJokes()!=null){
            Set<JokeDTO> jokesDTO = new HashSet<>();
            for (int i = 0; i < jokeCollection.getJokes().size(); i++) {
                jokesDTO.add(JokeMapper.toDTO(jokeCollection.getJokes().stream().toList().get(i)));
            }
            jokeCollectionDTO.setJokes(jokesDTO);
        }
        jokeCollectionDTO.setAuthor(AuthorMapper.toDTO(jokeCollection.getAuthor()));
        return jokeCollectionDTO;
    }
}
