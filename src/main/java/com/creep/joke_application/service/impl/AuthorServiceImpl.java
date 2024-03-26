package com.creep.joke_application.service.impl;

import com.creep.joke_application.mapper.AuthorMapper;
import com.creep.joke_application.model.Author;
import com.creep.joke_application.model.dto.AuthorRequestDTO;
import com.creep.joke_application.model.dto.AuthorResponseDTO;
import com.creep.joke_application.repository.AuthorRepository;
import com.creep.joke_application.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponseDTO postAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = AuthorMapper.toEntity(authorRequestDTO);
        return AuthorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
        List<AuthorResponseDTO> authorResponseDTOList = new ArrayList<>();
        for(Author author : authorList){
            authorResponseDTOList.add(AuthorMapper.toDTO(author));
        }
        return authorResponseDTOList;
    }

    @Override
    public Author getRawAuthorById(Long id) {
        return authorRepository.findAuthorById(id);
    }
    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        return AuthorMapper.toDTO(authorRepository.findAuthorById(id));
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        Author author = authorRepository.findAuthorById(id);
        if(author == null) {
            return null;
        }
        author.setFirstName(authorRequestDTO.getFirstName());
        author.setLastName(authorRequestDTO.getLastName());
        author.setNation(authorRequestDTO.getNation());
        return AuthorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteAuthorById(id);
    }
}
