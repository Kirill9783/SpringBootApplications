package com.example.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommentDto {

    private Integer id;
    private String text;
    private String author;

}
