package com.ssafy.gumibom.domain.meetingPost.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FindByGeoRequestDTO {

    private Double latitude;
    private Double longitude;
    private Double radius = 3.0;
    private List<String> categories = new ArrayList<>(Arrays.asList("taste", "healing", "culture", "active", "picture", "nature"));


}
