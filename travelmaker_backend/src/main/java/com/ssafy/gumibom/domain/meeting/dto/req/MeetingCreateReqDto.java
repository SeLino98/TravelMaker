package com.ssafy.gumibom.domain.meeting.dto.req;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import com.ssafy.gumibom.domain.meeting.entity.MeetingMember;
import com.ssafy.gumibom.domain.meetingPost.dto.DetailMeetingPostResForMeetingDto;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingApplier;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingPost;
import com.ssafy.gumibom.global.common.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingCreateReqDto {

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> categories;
    private String imgUrl;
    private Position position;
    private List<MeetingApplier> members;

    public MeetingCreateReqDto(DetailMeetingPostResForMeetingDto meeting){
        this.title = meeting.getTitle();
        this.startDate = meeting.getStartDate();
        this.endDate = meeting.getEndDate();
        this.categories = meeting.getCategories();
        this.imgUrl = meeting.getImgUrl();
        this.position = meeting.getPosition();
        this.members = meeting.getMembers();
    }

}

