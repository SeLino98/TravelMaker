package com.ssafy.gumibom.domain.pamphlet.controller;

import com.ssafy.gumibom.domain.pamphlet.dto.PersonalPamphletDto;
import com.ssafy.gumibom.domain.pamphlet.dto.request.MakePersonalPamphletRequestDto;
import com.ssafy.gumibom.domain.pamphlet.dto.response.MakePersonalPamphletResponseDto;
import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import com.ssafy.gumibom.domain.pamphlet.service.PersonalPamphletService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal-pamphlet")
@RequiredArgsConstructor
public class PersonalPamphletController {

    private final PersonalPamphletService pPamphletService;

    @Operation(summary = "개인 팜플렛 생성 ")
    @PostMapping
    public @ResponseBody MakePersonalPamphletResponseDto makePersonalPamphlet(@RequestBody MakePersonalPamphletRequestDto makePPReqDto) {
        Long pId = pPamphletService.makePamphlet(makePPReqDto);

        return new MakePersonalPamphletResponseDto(true, "개인 팜플렛 생성 성공", pId);
    }

    @Operation(summary = "특정 개인 팜플렛 조회")
    @GetMapping(value = "v1/{pamphletId}")
    public PersonalPamphletDto selectPersonalPamphletById(@PathVariable("pamphletId") Long pamphletId) {
        return pPamphletService.selectPamphletById(pamphletId);
    }

    @Operation(summary = "특정 사용자의 개인 팜플렛 조회")
    @GetMapping(value = "v2/{userId}")
    public List<PersonalPamphletDto> selectPersonalPamphletByUserId(@PathVariable("userId") Long userId) {
        return pPamphletService.selectPamphletByUserId(userId);
    }

    @Operation(summary = "특정 사용자의 팜플렛을 제외한 모든 개인 팜플렛 조회")
    @GetMapping(value = "v3/{userId}")
    public List<PersonalPamphletDto> selectAllPersonalPamphlet(@PathVariable("userId") Long userId) {
        return pPamphletService.selectAllPamphlet(userId);
    }
}
