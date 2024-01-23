package com.ssafy.gumibom.domain.pamphlet.entity;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
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

    @OneToOne
    @JoinColumn(name = "meetingPamphlet")
    private Meeting meeting;

    @OneToMany(mappedBy = "meetingPamphlet")
    private List<MeetingRecord> meetingRecords;
}
