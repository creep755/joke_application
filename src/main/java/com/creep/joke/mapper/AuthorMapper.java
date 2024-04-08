package com.creep.joke.mapper;

import com.creep.joke.model.Author;
import com.creep.joke.model.dto.AuthorRequestDTO;
import com.creep.joke.model.dto.AuthorResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    private AuthorMapper(){
    }

    public static AuthorResponseDTO toDTO(Author author){
        if (author == null){
            return null;
        }
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(author.getId());
        authorResponseDTO.setFirstName(author.getFirstName());
        authorResponseDTO.setLastName(author.getLastName());
        authorResponseDTO.setNation(author.getNation());
        return authorResponseDTO;
    }

    public static Author toEntity(AuthorRequestDTO authorRequestDTO){
        if(authorRequestDTO == null){
            return null;
        }
        Author author = new Author();
        author.setFirstName(authorRequestDTO.getFirstName());
        author.setLastName(authorRequestDTO.getLastName());
        author.setNation(authorRequestDTO.getNation());
        return author;
    }
}
