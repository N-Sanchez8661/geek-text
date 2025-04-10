package com.bookstore.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AverageRatingDTO {
    private Long bookID;
    private Double averageRating;
}
