package com.ssafy.gumibom.domain.meeting.dto;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import com.ssafy.gumibom.global.common.Category;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MeetingResDto {
    private Long id;
    private List<Category> categories;
    private String imgUrl;
    private Boolean status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long pamphletId;
//    private List<Category> categories; // Category DTO가 필요할 수 있습니다.


    public MeetingResDto(Meeting meeting) {
        this.id = meeting.getId();
        this.categories = meeting.getCategories();
        this.imgUrl = meeting.getImgUrl();
        this.status = meeting.getStatus();
        this.startDate = meeting.getStartDate();
        this.endDate = meeting.getEndDate();
        this.pamphletId = meeting.getMeetingPamphlet() != null ? meeting.getMeetingPamphlet().getId() : null;
        this.categories = meeting.getCategories();
    }
}
