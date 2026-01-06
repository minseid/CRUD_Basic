package com.example.crud.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PostRequest {
    private String author;
    private String title;
    private String content;
}
