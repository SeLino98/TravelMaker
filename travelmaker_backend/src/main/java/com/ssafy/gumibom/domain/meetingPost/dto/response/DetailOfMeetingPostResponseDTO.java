package com.ssafy.gumibom.domain.meetingPost.dto.response;

import com.ssafy.gumibom.domain.meetingPost.entity.MeetingApplier;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingPost;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.global.common.Position;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Getter
public class DetailOfMeetingPostResponseDTO {

    private Long headId;
    private String profileImgUrl;

    private String postTitle;
    private String postContent;
    private String mainImgUrl;
    private String subImgUrl;
    private String thirdImgUrl;
    private List<String> categories;
    private LocalDateTime startDate;
    private Position position;

    private Integer numOfNative;
    private Integer numOfTraveler;

    private Long dDay;

    public DetailOfMeetingPostResponseDTO(User head, MeetingPost meetingPost) {

        this.headId = head.getId();
        this.profileImgUrl = head.getImgURL();

        this.postTitle = meetingPost.getTitle();
        this.postContent = meetingPost.getContent();
        this.mainImgUrl = meetingPost.getImgUrlMain();
        this.subImgUrl = meetingPost.getImgUrlSub();
        this.thirdImgUrl = meetingPost.getImgUrlThr();
        this.categories = meetingPost.getCategories();
        this.startDate = meetingPost.getStartDate();
        this.position = meetingPost.getPosition();

        int[] numOfNatAndTrav = calcNatAndTrav(meetingPost.getAppliers());
        this.numOfNative = numOfNatAndTrav[0];
        this.numOfTraveler = numOfNatAndTrav[1];

        this.dDay = ChronoUnit.DAYS.between(meetingPost.getDeadline(), LocalDateTime.now())-1;
    }

    public int[] calcNatAndTrav(List<MeetingApplier> appliers) {
        int[] nums = new int[2]; // [0]은 native 수, [1]은 traveler 수

        for(MeetingApplier applier: appliers) {
            if(applier.getIsNative()) nums[0]++;
            else nums[1]++;
        }

        return nums;
    }
}
