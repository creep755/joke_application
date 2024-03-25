package com.creep.joke_application.mapper;

import com.creep.joke_application.model.Author;
import com.creep.joke_application.model.dto.AuthorDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    private AuthorMapper(){
    }
    //todo
    // добавить мапперы отдельно для .Response и .Request
    public static AuthorDTO toDTO(Author author){
        if (author == null){
            return null;
        }
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setNation(author.getNation());
        return authorDTO;
    }
}
