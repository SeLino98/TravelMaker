package com.ssafy.gumibom.domain.record.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavePersonalRecordRequestDto {

    @NotBlank
    private Long pamphletId;

    @NotBlank
    private String title;

    @NotBlank
    private String text;
}
