package com.alepo.geek_test.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Integer bookId;
    public void setBookId(Integer bookId){
        this.bookId = bookId;
    }

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("title")
    private String title;
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;
    public void setPrice(Double price){ this.price = price; }
    public Double getPrice(){ return price; }

    @JsonProperty("genre")
    private String genre;
    public void setGenre(String genre){ this.genre = genre; }

    @JsonProperty("publisher")
    private String publisher;
    public void setPublisher(String publisher){ this.publisher = publisher; }
    public String getPublisher(){ return publisher; }

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("copies")
    private Integer copies;
}
