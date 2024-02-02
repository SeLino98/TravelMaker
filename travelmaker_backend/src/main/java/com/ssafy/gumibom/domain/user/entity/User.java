package com.ssafy.gumibom.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.gumibom.domain.meeting.entity.MeetingMember;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingApplier;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingPost;
import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import com.ssafy.gumibom.domain.user.dto.req.SignupRequestDto;
import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Nation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;
    @Email
    private String email;

    @NotEmpty
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender; // Gender는 enum 타입

    private String birth;

    @NotEmpty
    @Size(min = 10, max = 15)
    private String phone;

    @Lob
    private String imgURL;

    private double belief;

    private String town;

    @Lob
    private String fcmtoken;

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
<<<<<<< HEAD

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeetingApplier> meetingAppliers = new ArrayList<>();
=======
>>>>>>> feature/record

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MeetingMember> meetingMembers = new ArrayList<>();

    // Gender enum 타입 정의
    public enum Gender {
        MALE, FEMALE
    }


//    @Builder
//    public User(SignupRequestDto signupRequestDto){
//        this.username = signupRequestDto.getUsername();
//        this.password = signupRequestDto.getPassword();
//        this.email = signupRequestDto.getEmail();
//        this.gender = signupRequestDto.getGender();
//        this.phone = signupRequestDto.getPhone();
//        this.nation = signupRequestDto.getNation();
//        this.categories = signupRequestDto.getCategories();
//        this.imgURL = signupRequestDto.getProfileImgURL();
//    }
//
//    //token 생성시 사용될 생성자
//    @Builder
//    public User(String username, String password, Role role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }

//    public User(String loginId, String password, String nickname, String birth, String phone, boolean gender, List<Category> categories, Nation nation) {
//    }
//
//    public User(String subject, String password, Collection<? extends GrantedAuthority> authorities) {
//    }
//
//
//    public void setPassword(String password) {
//        this.password = password;
//    }


//    @JsonIgnore
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<MeetingPost> meetingPosts;

}
