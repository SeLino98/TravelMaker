package com.ssafy.gumibom.domain.pamphlet.entity;

import com.ssafy.gumibom.domain.record.entity.PersonalRecord;
import com.ssafy.gumibom.domain.record.entity.Record;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.global.common.Category;
import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "P")
public class PersonalPamphlet extends Pamphlet {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    private ArrayList<Integer> categories;

    @OneToMany(mappedBy = "personalPamphlet", cascade = CascadeType.ALL)
    private List<PersonalRecord> personalRecords = new ArrayList<>();

    @Override
    public void addRecord(Record record) {
        this.personalRecords.add((PersonalRecord) record);
    }

    @Override
    public void removeRecord(Record record) {
        this.personalRecords.remove((PersonalRecord) record);
    }
}
