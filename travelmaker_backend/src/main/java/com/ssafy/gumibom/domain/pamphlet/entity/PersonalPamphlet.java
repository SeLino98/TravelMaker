package com.ssafy.gumibom.domain.pamphlet.entity;

import com.ssafy.gumibom.global.common.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalPamphlet {

    @Id @GeneratedValue
    @Column(name = "ppamphlet_id")
    private long id;

    private long userId; // 임시 데이터 타입

    @ElementCollection
    private List<Category> categories;

    private LocalDateTime createDate;
    private String title;
    private int like;
}
