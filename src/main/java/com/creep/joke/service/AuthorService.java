package com.creep.joke.service;

import com.creep.joke.model.Author;
import com.creep.joke.model.dto.AuthorRequestDTO;
import com.creep.joke.model.dto.AuthorResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AuthorService {
    AuthorResponseDTO postAuthor(AuthorRequestDTO author);
    List<AuthorResponseDTO> getAllAuthors();
    AuthorResponseDTO getAuthorById(Long id);
    Author getRawAuthorById(Long id);
    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO author);
    void deleteAuthorById(Long id);
}
