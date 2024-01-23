package com.ssafy.gumibom.global.common;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
// 값 타입 -> 변경 불가능하게 설계 (No setter)
public class Emoji {

    private String emotion;
    private String imgUrl;

    protected Emoji() {}

    public Emoji(String emotion, String imgUrl) {
        this.emotion = emotion;
        this.imgUrl = imgUrl;
    }
}
