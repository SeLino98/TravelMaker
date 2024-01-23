package com.ssafy.gumibom.domain.pamphlet.entity;

import com.ssafy.gumibom.domain.record.entity.MeetingRecord;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue(value = "M")
public class MeetingPamphlet extends Pamphlet {

    private long meetingId; // 임시 데이터 타입

    @OneToMany(mappedBy = "meetingPamphlet")
    private List<MeetingRecord> meetingRecords;
}
