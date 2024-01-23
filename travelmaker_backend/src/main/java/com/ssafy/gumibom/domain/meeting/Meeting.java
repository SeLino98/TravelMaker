package com.ssafy.gumibom.domain.meeting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.gumibom.global.common.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    @Id @GeneratedValue
    @Column(name = "meeting_id")
    private long id;

    @ElementCollection
    private List<Category> categories;

    private String imgUrl;
    private boolean status; // 모임 진행 중인지, 모임 완료되었는지
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @JsonIgnore
    @OneToMany(mappedBy = "meeting")
    private List<MeetingMember> meetingMembers;
}
