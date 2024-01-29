package com.ssafy.gumibom.domain.meetingPost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Position;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingPost {

    @Id
    @GeneratedValue
    @Column(name = "meeting_post_id")
    private Long id;

    //    @JsonIgnore
//    @OneToMany(mappedBy = "meeting_post")
    @ElementCollection
    private List<Category> categories = new ArrayList<>();

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
    private String imgUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "meeting_post", cascade = CascadeType.ALL)
    private List<MeetingApplier> appliers = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "meeting_post", cascade = CascadeType.ALL)
    private Position position;

    public void addApplier(User user, Boolean isHead, Position position) {
        MeetingApplier meetingApplier = new MeetingApplier();
        meetingApplier.setUser(user);
        meetingApplier.setMeetingPost(this);
        meetingApplier.setIsNative(position.getTown() == user.getTown());
        meetingApplier.setIsHead(isHead);
        appliers.add(meetingApplier);
    }

    public static MeetingPost writeMeetingPost(User author, String title, String content, LocalDateTime authDate,
                                               Integer nativeMin, Integer travelerMin, Integer memberMax,
                                               LocalDateTime startDate, LocalDateTime endDate, Position position,
                                               LocalDateTime deadline, String imgUrl, List<Category> categories) {

        MeetingPost meetingPost = new MeetingPost();

        meetingPost.addApplier(author, true, position);

        meetingPost.setTitle(title);
        meetingPost.setContent(content);
        meetingPost.setAuthDate(authDate);
        meetingPost.setNativeMin(nativeMin);
        meetingPost.setTravelerMin(travelerMin);
        meetingPost.setMemberMax(memberMax);
        meetingPost.setStartDate(startDate);
        meetingPost.setEndDate(endDate);
        meetingPost.setPosition(position);
        meetingPost.setDeadline(deadline);
        meetingPost.setImgUrl(imgUrl);
        meetingPost.setCategories(categories);

        return meetingPost;
    }


}
