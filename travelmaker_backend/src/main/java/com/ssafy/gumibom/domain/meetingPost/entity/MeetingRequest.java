package com.ssafy.gumibom.domain.meetingPost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.gumibom.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "meeting_request", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "meeting_post_id"})
})
@Getter
public class MeetingRequest {

    @Id
    @GeneratedValue
    @Column(name = "meeting_request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_post_id")
    private MeetingPost meetingPost;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User respondent;

//    private boolean isHead;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User requestor;
}
