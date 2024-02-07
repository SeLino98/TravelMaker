package com.ssafy.gumibom.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountModifyRequestDTO {

    private String userLoginId;
    private String modifyField;
}
