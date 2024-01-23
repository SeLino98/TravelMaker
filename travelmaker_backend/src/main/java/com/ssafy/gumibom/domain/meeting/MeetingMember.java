package com.ssafy.gumibom.domain.meeting;


import com.ssafy.gumibom.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MeetingMember {

    @Id @GeneratedValue
    @Column(name = "meeting_member_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isNative;
}
