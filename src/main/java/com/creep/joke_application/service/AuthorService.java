package com.creep.joke_application.service;

import com.creep.joke_application.model.Author;
import com.creep.joke_application.model.dto.AuthorRequestDTO;
import com.creep.joke_application.model.dto.AuthorResponseDTO;
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
