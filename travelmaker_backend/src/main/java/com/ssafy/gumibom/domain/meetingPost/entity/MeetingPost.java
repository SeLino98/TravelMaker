package com.ssafy.gumibom.domain.meetingPost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.gumibom.domain.user.User;
import com.ssafy.gumibom.global.common.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingPost {

    @Id @GeneratedValue
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

    public void addApplier(MeetingApplier meetingApplier) {
        appliers.add(meetingApplier);
        meetingApplier.setMeetingPost(this);
    }



    public static MeetingPost writeMeetingPost(User author, String title, String content, LocalDateTime authDate,
                                               Integer nativeMin, Integer travelerMin, Integer memberMax,
                                               LocalDateTime startDate, LocalDateTime endDate,
                                               LocalDateTime deadline, String imgUrl, List<Category> categories) {

        MeetingPost meetingPost = new MeetingPost();
        MeetingApplier meetingApplier = MeetingApplier.createMeetingApplier(author, Boolean.TRUE, this);

        meetingPost.appliers.add(meetingApplier);
    }

}
