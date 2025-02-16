package com.alepo.geek_text_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private String publisher;
    private String date;
    private String genre;
    private String isbn;
    private Integer copies;

}
