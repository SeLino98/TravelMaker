package com.ssafy.gumibom.domain.record.entity;

import com.ssafy.gumibom.domain.pamphlet.entity.Pamphlet;
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
public abstract class Record {

    @Id @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    private String title;
    private LocalDateTime createDate;
    private String imgUrl;
    private String text;
    private String videoUrl;

    @Embedded
    private Emoji emoji;

    private String testCode1;
    private String testCodeForCodeReview;

    public abstract void setPamphlet(Pamphlet pamphlet);
}
