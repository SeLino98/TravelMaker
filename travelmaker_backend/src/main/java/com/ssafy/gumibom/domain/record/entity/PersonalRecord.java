package com.ssafy.gumibom.domain.record.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class PersonalRecord extends Record {

    private long personalPamphletId; // 임시 데이터 타입
    private long userId; // 임시 데이터 타입
}
