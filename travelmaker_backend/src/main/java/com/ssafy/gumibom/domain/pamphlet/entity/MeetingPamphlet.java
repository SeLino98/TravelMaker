package com.ssafy.gumibom.domain.pamphlet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingPamphlet {

    @Id @GeneratedValue
    @Column(name = "mpamphlet_id")
    private long id;

    private long meetingId; // 임시 데이터 타입

    private LocalDateTime createDate;
    private String title;
    private int love;
}
