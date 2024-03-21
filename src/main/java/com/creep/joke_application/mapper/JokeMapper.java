package com.creep.joke_application.mapper;

import com.creep.joke_application.model.Joke;
import com.creep.joke_application.model.JokeCollection;
import com.creep.joke_application.model.dto.JokeDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JokeMapper {
    private JokeMapper(){
    }
    public static JokeDTO toDTO(Joke joke){
        if (joke == null) {
            return null;
        }
        JokeDTO jokeDTO = new JokeDTO();
        jokeDTO.setId(joke.getId());
        jokeDTO.setLang(joke.getLang());
        jokeDTO.setType(joke.getType());
        jokeDTO.setAuthor(joke.getAuthor());
        Set<Long> jokeCollectionsId = new HashSet<>();
        for(int i = 0; i< joke.getJokeCollections().size(); i++){
            jokeCollectionsId.add(joke.getJokeCollections().stream().toList().get(i).getId());
        }
        jokeDTO.setJokeCollectionsId(jokeCollectionsId);
        jokeDTO.setSetup(joke.getSetup());
        jokeDTO.setPunchline(joke.getPunchline());
        return jokeDTO;
    }
   /* public Joke toEntity(JokeDTO jokeDTO){
        if (jokeDTO == null) {
            return null;
        }
        Joke joke = new Joke();
        joke.setId(jokeDTO.getId());
        joke.setLang(jokeDTO.getLang());
        joke.setType(jokeDTO.getType());
        joke.setAuthor(jokeDTO.getAuthor());
        Set<JokeCollection> jokeCollections = new HashSet<>();
        for (int i = 0; i< jokeDTO.getJokeCollectionsId().size(); i++){

        }
        joke.setJokeCollections(jokeCollections);
        joke.setSetup(jokeDTO.getSetup());
        joke.setPunchline(jokeDTO.getPunchline());
        return joke;
    }*/

}
