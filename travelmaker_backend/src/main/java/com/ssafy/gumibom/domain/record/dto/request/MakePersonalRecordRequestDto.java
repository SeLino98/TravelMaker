package com.ssafy.gumibom.domain.record.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MakePersonalRecordRequestDto {

    @NotBlank
    private Long pamphletId;

    @NotBlank
    private String title;

    @NotBlank
    private String text;
}
