package com.ssafy.gumibom.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.gumibom.domain.meeting.entity.MeetingMember;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingApplier;
import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Nation;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;
    private String email;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender; // Gender는 enum 타입

    private String birth;
    private String phoneNum;
    private String imgURL;
    private String town;

    @Embedded
    private Nation nation;

    @ElementCollection
    private List<Category> categories;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonalPamphlet> personalPamphlets;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<MeetingPost> meetingPosts;

    @OneToOne(fetch = FetchType.LAZY)
    private MeetingApplier meetingApplier;

    @OneToOne(fetch = FetchType.LAZY)
    private MeetingMember meetingMember;

    // Gender enum 타입 정의
    public enum Gender {
        MALE, FEMALE
    }


}
