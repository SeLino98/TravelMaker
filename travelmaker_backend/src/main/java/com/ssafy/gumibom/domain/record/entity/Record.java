package com.ssafy.gumibom.domain.record.entity;

import com.ssafy.gumibom.domain.pamphlet.entity.Pamphlet;
import com.ssafy.gumibom.global.common.Emoji;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor
public abstract class Record {

    @Id @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    private String title;
    private LocalDateTime createDate;
    private String imgUrl;
    private String videoUrl;
    private String text;

//    @Embedded
//    private Emoji emoji;

    protected void setRecord(String title, String imgUrl, String videoUrl, String text) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.text = text;
        this.createDate = LocalDateTime.now();
    }

    protected abstract void setPamphlet(Pamphlet pamphlet);
}
