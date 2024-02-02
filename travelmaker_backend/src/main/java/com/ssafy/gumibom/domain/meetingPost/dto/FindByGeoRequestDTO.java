package com.ssafy.gumibom.domain.meetingPost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class FindByGeoRequestDTO {

    private Double latitude;
    private Double longitude;
    //    @Builder.Default
    private Double radius = 3.0;
    //    @Builder.Default
    private List<String> categories = new ArrayList<>(Arrays.asList("taste", "healing", "culture", "active", "picture", "nature"));

    public FindByGeoRequestDTO() {
        radius = 3.0;
        categories = new ArrayList<>(Arrays.asList("taste", "healing", "culture", "active", "picture", "nature"));
    }
}
