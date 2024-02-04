package com.ssafy.gumibom.domain.record.dto;

import com.ssafy.gumibom.domain.record.entity.PersonalRecord;
import com.ssafy.gumibom.domain.record.entity.Record;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PersonalRecordDto {

    private String title;
    private LocalDateTime createTime;
    private String imgUrl;
    private String videoUrl;
    private String text;

    public PersonalRecordDto(PersonalRecord record) {
        this.title = record.getTitle();
        this.createTime = record.getCreateTime();
        this.imgUrl = record.getImgUrl();
        this.videoUrl = record.getVideoUrl();
        this.text = record.getText();
    }
}
