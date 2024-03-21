package com.creep.joke_application.mapper;

import com.creep.joke_application.model.Author;
import com.creep.joke_application.model.dto.AuthorDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    private AuthorMapper(){
    }
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
    public static Author toEntity(AuthorDTO authorDTO){
        if (authorDTO == null){
            return null;
        }
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setNation(authorDTO.getNation());
        return author;
    }
}
