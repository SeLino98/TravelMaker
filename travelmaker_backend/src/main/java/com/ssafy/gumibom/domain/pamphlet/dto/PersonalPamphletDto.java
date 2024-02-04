package com.ssafy.gumibom.domain.pamphlet.dto;

import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import com.ssafy.gumibom.domain.record.dto.PersonalRecordDto;
import com.ssafy.gumibom.domain.record.entity.Record;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@EqualsAndHashCode(of = "pamphletId")
public class PersonalPamphletDto {

    private Long pamphletId;
    private String title;
    private Integer love;
    private LocalDateTime createTime;
    private List<PersonalRecordDto> records;

    public PersonalPamphletDto(PersonalPamphlet pPamphlet) {
        this.pamphletId = pPamphlet.getId();
        this.title = pPamphlet.getTitle();
        this.love = pPamphlet.getLove();
        this.createTime = pPamphlet.getCreateTime();
        this.records = pPamphlet.getPersonalRecords().stream()
                .map(record -> new PersonalRecordDto(record))
                .collect(Collectors.toList());
    }

}
