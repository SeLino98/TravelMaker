package com.ssafy.gumibom.domain.meeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.gumibom.domain.meeting.dto.req.MeetingCreateReqDto;
import com.ssafy.gumibom.domain.pamphlet.entity.MeetingPamphlet;
import com.ssafy.gumibom.global.common.Position;
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

    @Id
    @GeneratedValue
    @Column(name = "meeting_id")
    private Long id;

    @ElementCollection
    private List<String> categories;

    private String title;
    private String imgUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean status; // 모임 진행 중인지, 모임 완료되었는지

    @Embedded
    private Position position;

    @JsonIgnore
    @OneToMany(mappedBy = "meeting")
    private List<MeetingMember> meetingMembers;

    @OneToOne
    @JoinColumn(name = "pamphlet_id")
    private MeetingPamphlet meetingPamphlet;


    public Meeting(MeetingCreateReqDto meetingCreateReqDto) {
        this.title = meetingCreateReqDto.getTitle();
        this.startDate = meetingCreateReqDto.getStartDate();
        this.endDate = meetingCreateReqDto.getEndDate();
        this.imgUrl = meetingCreateReqDto.getImgUrl();
        this.categories = meetingCreateReqDto.getCategories();
        this.status = true;
        this.position = meetingCreateReqDto.getPosition();
    }
}
