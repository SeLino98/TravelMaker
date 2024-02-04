package com.ssafy.gumibom.domain.pamphlet.controller;

import com.ssafy.gumibom.domain.pamphlet.dto.request.MakePersonalPamphletRequestDto;
import com.ssafy.gumibom.domain.pamphlet.dto.response.MakePersonalPamphletResponseDto;
import com.ssafy.gumibom.domain.pamphlet.service.PersonalPamphletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal-pamphlet")
@RequiredArgsConstructor
public class PersonalPamphletController {

    private final PersonalPamphletService pPamphletService;

    @PostMapping
    public @ResponseBody MakePersonalPamphletResponseDto makePersonalPamphlet(@RequestBody MakePersonalPamphletRequestDto makePPReqDto) {
        Long pId = pPamphletService.makePamphlet(makePPReqDto);

        return new MakePersonalPamphletResponseDto(true, "개인 팜플렛 생성 성공", pId);
    }
}
