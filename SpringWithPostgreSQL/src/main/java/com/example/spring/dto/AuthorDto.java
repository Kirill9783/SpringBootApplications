package com.example.spring.dto;

import lombok.Data;

import java.util.List;

@Data //автоматически создаются геттеры, сеттеры и прочее
public class AuthorDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private Long rating;
    private List<CommentDto> comments;
}
