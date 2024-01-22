package com.ssafy.gumibom.domain.record.entity;

import com.ssafy.gumibom.domain.pamphlet.entity.MeetingPamphlet;
import com.ssafy.gumibom.domain.user.User;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("M")
public class MeetingRecord extends Record {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mpamphlet_id")
    private MeetingPamphlet meetingPamphlet;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn("user_id")
    private User user; // 임시 데이터 타입
}
