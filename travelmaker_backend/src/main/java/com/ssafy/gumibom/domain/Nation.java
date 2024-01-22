package com.ssafy.gumibom.domain;

import jakarta.persistence.*;

@Embeddable
public class Nation {
    @Id @GeneratedValue
    @Column(name = "nation_id")
    private int id;

    private String name;

    private String nationImg;
}
