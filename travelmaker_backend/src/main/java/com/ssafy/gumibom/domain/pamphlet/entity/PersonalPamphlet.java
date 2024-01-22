package com.ssafy.gumibom.domain.pamphlet.entity;

import com.ssafy.gumibom.domain.record.entity.PersonalRecord;
import com.ssafy.gumibom.domain.user.User;
import com.ssafy.gumibom.global.common.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalPamphlet {

    @Id @GeneratedValue
    @Column(name = "ppamphlet_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    private List<Category> categories;

    private LocalDateTime createDate;
    private String title;
    private int like;

    @OneToMany(mappedBy = "personalPamphlet", cascade = CascadeType.ALL)
    private List<PersonalRecord> personalRecords = new ArrayList<>();
}
