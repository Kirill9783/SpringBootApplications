package com.example.spring.services;

import com.example.spring.dto.AuthorDto;
import com.example.spring.entity.Author;
import com.example.spring.repositories.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthorCRUDServiceTest {

    private final AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
    private final AuthorCRUDService authorCRUDService = new AuthorCRUDService(authorRepository);

    @Test
    @DisplayName("Test author getById")
    public void testGetById() {
        int authorId= 1;
        Author author = new Author();
        author.setId(authorId);
        author.setComments(List.of());
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        AuthorDto authorDto = authorCRUDService.getById(authorId);
        assertEquals(authorId, authorDto.getId());
        //проверяем, что метод findById реально выполнил запрос к нашему репозиторию 1 раз, чтобы найти
        //автора с указанным authorId
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    @DisplayName("Test author getAll")
    public void testGetAll() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setId(1);
        author.setComments(List.of());
        authors.add(author);
        when(authorRepository.findAll()).thenReturn(authors);
        Collection<AuthorDto> authorDtos = authorCRUDService.getAll();
        assertEquals(authors.size(), authorDtos.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test author create")
    public void testCreate() {
        AuthorDto authorDto = new AuthorDto();
        authorCRUDService.create(authorDto);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    @DisplayName("Test author update")
    public void testUpdate() {
        AuthorDto authorDto = new AuthorDto();
        authorCRUDService.update(authorDto);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    @DisplayName("Test author deleteById")
    public void testDeleteById() {
        int authorId = 1;
        authorCRUDService.delete(authorId);
        verify(authorRepository, times(1)).deleteById(authorId);
    }

}
