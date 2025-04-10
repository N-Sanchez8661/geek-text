package com.bookstore.bookstore.dto;

import com.bookstore.bookstore.model.Books;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private String content;
    private Books book;
}
