package com.example.spring.services;

import com.example.spring.dto.CommentDto;
import com.example.spring.entity.Author;
import com.example.spring.entity.Comment;
import com.example.spring.repositories.AuthorRepository;
import com.example.spring.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentCRUDService implements CRUDService<CommentDto> {

    @Value("${comment.length.max}")
    private Integer maxLength;

    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;

    @Override
    public CommentDto getById(Integer id) {
        log.info("Get by ID: {}", id);
        Comment comment = commentRepository.findById(id).orElseThrow();
        return mapToDto(comment);
    }

    @Override
    public Collection<CommentDto> getAll() {
        log.info("Get all");
        return commentRepository.findAll()
                .stream()
                .map(CommentCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(CommentDto commentDto) {
        log.info("Create");
        Comment comment = mapToEntity(commentDto);
        Integer authorId = commentDto.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow();
        comment.setAuthor(author);
        commentRepository.save(comment);
    }

    @Override
    public void update(CommentDto commentDto) {
        log.info("Update");
        Comment comment = mapToEntity(commentDto);
        Integer authorId = commentDto.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow();
        comment.setAuthor(author);
        commentRepository.save(comment);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete {}", id);
        commentRepository.deleteById(id);
    }

    public static CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setAuthorId(comment.getAuthor().getId());
        return commentDto;
    }

    public static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        return comment;
    }
}
