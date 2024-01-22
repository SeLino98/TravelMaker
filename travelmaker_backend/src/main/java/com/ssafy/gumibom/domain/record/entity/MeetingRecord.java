package com.ssafy.gumibom.domain.record.entity;

import com.ssafy.gumibom.domain.pamphlet.entity.MeetingPamphlet;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("M")
public class MeetingRecord extends Record {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mpamphlet_id")
    private MeetingPamphlet meetingPamphlet;

    private long userId; // 임시 데이터 타입
}
