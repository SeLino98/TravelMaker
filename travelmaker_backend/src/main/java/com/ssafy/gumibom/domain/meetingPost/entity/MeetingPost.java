package com.ssafy.gumibom.domain.meetingPost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.gumibom.domain.meetingPost.dto.WriteMeetingPostRequestDTO;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.global.common.Position;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_post_id")
    private Long id;

    //    @JsonIgnore
//    @OneToMany(mappedBy = "meeting_post")
    @ElementCollection
    private List<String> categories = new ArrayList<>();

    private String title;
    private String content;
    private LocalDateTime authDate;
    private Integer nativeMin;
    private Integer travelerMin;
    private Integer memberMax;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean status;
    private LocalDateTime deadline;
    private String imgUrlMain;
    private String imgUrlSub;
    private String imgUrlThr;

    @JsonIgnore
    @OneToMany(mappedBy = "meetingPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeetingApplier> appliers = new ArrayList<>();

//    @JsonIgnore
//    @OneToOne(mappedBy = "meeting_post", cascade = CascadeType.ALL)
    @Embedded
    private Position position;

    public MeetingPost(WriteMeetingPostRequestDTO requestDTO) {

        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.authDate = requestDTO.getAuthDate();
        this.deadline = requestDTO.getDeadline();
        this.startDate = requestDTO.getStartDate();
        this.endDate = requestDTO.getEndDate();
        this.nativeMin = requestDTO.getNativeMin();
        this.travelerMin = requestDTO.getTravelerMin();
        this.memberMax = requestDTO.getMemberMax();
        this.status = false;
        this.imgUrlMain = requestDTO.getImgUrlMain();
        this.imgUrlSub = requestDTO.getImgUrlSub();
        this.imgUrlThr = requestDTO.getImgUrlThr();
        this.categories = requestDTO.getCategories();
        this.position = requestDTO.getPosition();
    }

    public void addApplier(User user, Boolean isHead, Position position) {
        MeetingApplier meetingApplier = new MeetingApplier();
        meetingApplier.setUser(user);
        meetingApplier.setMeetingPost(this);
        meetingApplier.setIsNative(position.getTown() == user.getTown());
        meetingApplier.setIsHead(isHead);
        appliers.add(meetingApplier);
    }

    public static MeetingPost createMeetingPost(WriteMeetingPostRequestDTO requestDTO, User author
    ) {

        MeetingPost meetingPost = new MeetingPost(requestDTO);

        meetingPost.addApplier(author, true, requestDTO.getPosition());

        return meetingPost;
    }

    public MeetingPost updateMeetingPost(WriteMeetingPostRequestDTO requestDTO) {

        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.authDate = requestDTO.getAuthDate();

        this.deadline = requestDTO.getDeadline();
        // 기간 변경에 따른 status 변경
        if (this.deadline != null) {
            this.status = this.deadline.isBefore(LocalDateTime.now());
        } else {
            this.status = false;
        }

        this.startDate = requestDTO.getStartDate();
        this.endDate = requestDTO.getEndDate();
        this.nativeMin = requestDTO.getNativeMin();
        this.travelerMin = requestDTO.getTravelerMin();
        this.memberMax = requestDTO.getMemberMax();
        this.imgUrlMain = requestDTO.getImgUrlMain();
        this.imgUrlSub = requestDTO.getImgUrlSub();
        this.imgUrlThr = requestDTO.getImgUrlThr();
        this.categories = requestDTO.getCategories();
        this.position = requestDTO.getPosition();

        return this;
    }

    public void updateMeetingPostStatus(Boolean newStatus) {
        this.status = newStatus;
    }
}
