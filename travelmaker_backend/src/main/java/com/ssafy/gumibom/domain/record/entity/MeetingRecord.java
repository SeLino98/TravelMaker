package com.ssafy.gumibom.domain.record.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class MeetingRecord extends Record {

    private long meetingPamphletId; // 임시 데이터 타입
    private long userId; // 임시 데이터 타입
}
