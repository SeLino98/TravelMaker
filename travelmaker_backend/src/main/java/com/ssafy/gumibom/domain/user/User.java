package com.ssafy.gumibom.domain.user;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import com.ssafy.gumibom.domain.meeting.entity.MeetingMember;
import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Nation;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;
    private String email;
    private String nickname;
    private boolean gender;
    private String birth;
    private String phoneNum;
    private String imgURL;
    private String town;

    @Embedded
    private Nation nation;

    @ElementCollection
    private List<Category> categories;

    @OneToMany(mappedBy = "user")
    private List<MeetingMember> meetingMemberList = new ArrayList<>();

    // 연관관계 편의 메서드
    public void setMeetingMember(MeetingMember meetingMember) {
        meetingMemberList.add(meetingMember);
        meetingMember.setUser(this);
    }
}
