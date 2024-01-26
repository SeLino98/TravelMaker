package com.ssafy.gumibom.global.common;

import jakarta.persistence.*;

@Embeddable
public class Category {
//    @Id @GeneratedValue
//    @Column(name = "category_id")
//    private int id;

    private String name;
    private String color;

}
