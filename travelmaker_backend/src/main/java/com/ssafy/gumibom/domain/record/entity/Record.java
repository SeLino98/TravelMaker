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

//    public Record(String title, String text, Emoji emoji) {
//        this.title = title;
//        this.text = text;
//        this.emoji = emoji;
//    }

    public Record(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public void setVideo(String videoUrl) {
        this.videoUrl = videoUrl;
    };
    public void setImage(String imgUrl) {
        this.imgUrl = imgUrl;
    };

    public abstract void setPamphlet(Pamphlet pamphlet);
}
