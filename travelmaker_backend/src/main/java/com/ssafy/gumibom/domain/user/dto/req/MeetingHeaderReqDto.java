package com.ssafy.gumibom.domain.user.dto.req;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingHeaderReqDto {

    // 모집글의 id로 meetingApplier에서 header의 boolean 값이 1인 사람의 userId를 찾아서 반환해줘야 함
    private Long meetingPostId;
}
