package com.ssafy.gumibom.domain.record.entity;

import com.ssafy.gumibom.domain.pamphlet.entity.MeetingPamphlet;
import com.ssafy.gumibom.domain.pamphlet.entity.Pamphlet;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("M")
public class MeetingRecord extends Record {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pamphlet_id")
    private MeetingPamphlet meetingPamphlet;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn("user_id")
//    private User user; // 임시 데이터 타입

    // 연관관계 편의 메서드
    @Override
    public void setPamphlet(Pamphlet pamphlet) {
        if(this.meetingPamphlet!=null) {
            this.meetingPamphlet.removeRecord(this);
        }
        this.meetingPamphlet = (MeetingPamphlet) pamphlet;
        pamphlet.addRecord(this);
    }
}
