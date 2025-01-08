package com.example.spring.controllers;

import com.example.spring.dto.CommentDto;
import com.example.spring.services.CommentCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentCRUDService commentService;

    public CommentController(CommentCRUDService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public CommentDto getCommentById(@PathVariable Integer id) {
        return commentService.getById(id);
    }

    @GetMapping
    public Collection<CommentDto> getAllComments() {
        return commentService.getAll();
    }

    @PostMapping
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.create(commentDto);
    }

    @PutMapping("/{id}")
    public void updateComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        commentService.update(id, commentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.delete(id);
    }
}
