package com.ssafy.gumibom.domain.record.entity;

import com.ssafy.gumibom.domain.pamphlet.entity.Pamphlet;
import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("P")
public class PersonalRecord extends Record {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pamphlet_id")
    private PersonalPamphlet personalPamphlet;

    // 연관관계 편의 메서드
    @Override
    public void setPamphlet(Pamphlet pamphlet) {
        if(this.personalPamphlet!=null) {
            this.personalPamphlet.removeRecord(this);
        }
        this.personalPamphlet = (PersonalPamphlet) pamphlet;
        pamphlet.addRecord(this);
    }
}
