package com.ssafy.gumibom.domain.meetingPost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.gumibom.domain.meetingPost.dto.WriteMeetingPostRequestDTO;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Position;
import jakarta.persistence.*;
import lombok.*;

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
    @OneToMany(mappedBy = "meetingPost", cascade = CascadeType.ALL)
    private List<MeetingApplier> appliers = new ArrayList<>();

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

    /* 분리수거 직전
public static MeetingPost createMeetingPost(WriteMeetingPostRequestDTO requestDTO, User author
//                                                , String title, String content, LocalDateTime authDate,
//                                                Integer nativeMin, Integer travelerMin, Integer memberMax,
//                                                LocalDateTime startDate, LocalDateTime endDate, Position position,
//                                                LocalDateTime deadline, String imgUrlMain, String imgUrlSub, String imgUrlThr, List<Category> categories
    ) {

        MeetingPost meetingPost = new MeetingPost(requestDTO);

        meetingPost.addApplier(author, true, requestDTO.getPosition());

//        meetingPost.setTitle(requestDTO.getTitle());
//        meetingPost.setContent(requestDTO.getContent());
//        meetingPost.setAuthDate(requestDTO.getAuthDate());
//        meetingPost.setNativeMin(requestDTO.getNativeMin());
//        meetingPost.setTravelerMin(requestDTO.getTravelerMin());
//        meetingPost.setMemberMax(requestDTO.getMemberMax());
//        meetingPost.setStartDate(requestDTO.getStartDate());
//        meetingPost.setEndDate(requestDTO.getEndDate());
//        meetingPost.setPosition(requestDTO.getPosition());
//        meetingPost.setDeadline(requestDTO.getDeadline());
//        meetingPost.setImgUrlMain(imgUrlMain);
//        meetingPost.setImgUrlSub(imgUrlSub);
//        meetingPost.setImgUrlThr(imgUrlThr);
//        meetingPost.setCategories(categories);

        return meetingPost;
    }
     */


    public void updateMeetingPostStatus(Boolean newStatus) {
        this.status = newStatus;
    }
}
