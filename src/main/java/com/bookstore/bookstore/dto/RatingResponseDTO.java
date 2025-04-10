package com.bookstore.bookstore.dto;

import com.bookstore.bookstore.model.Books;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RatingResponseDTO {
    private Long id;
    private int score;
    private Books book;

    public static RatingResponseDTO fromEntity(com.bookstore.bookstore.model.Rating rating) {
        return new RatingResponseDTO(
                rating.getId(),
                rating.getScore(),
                rating.getBook()
        );
    }
}
