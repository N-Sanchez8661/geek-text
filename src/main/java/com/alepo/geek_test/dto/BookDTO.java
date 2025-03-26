package com.alepo.geek_test.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String title;
    public String getTitle(){ return title; }
}
