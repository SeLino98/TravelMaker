//package com.ssafy.gumibom.domain.meetingPost;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.ssafy.gumibom.domain.user.User;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter @Setter
//public class MeetingApplier {
//
//    @Id @GeneratedValue
//    @Column(name = "meeting_applier_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "meeting_post_id")
//    private MeetingPost meetingPost;
//
//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//
//    private Boolean isNative;
//    private Boolean isHead;
//}
