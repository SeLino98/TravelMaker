package com.ssafy.gumibom.domain.record.entity;

import com.ssafy.gumibom.global.common.Emoji;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @Id @GeneratedValue
    @Column(name = "record_id")
    private long id;

    private String title;
    private LocalDateTime createDate;
    private String imgUrl;
    private String text;
    private String videoUrl;

    @Embedded
    private Emoji emoji;
}
