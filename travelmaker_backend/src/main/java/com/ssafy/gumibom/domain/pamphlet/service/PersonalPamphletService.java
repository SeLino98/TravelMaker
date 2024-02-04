package com.ssafy.gumibom.domain.pamphlet.service;

import com.ssafy.gumibom.domain.pamphlet.dto.PersonalPamphletDto;
import com.ssafy.gumibom.domain.pamphlet.dto.request.MakePersonalPamphletRequestDto;
import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import com.ssafy.gumibom.domain.pamphlet.repository.PersonalPamphletRepository;
import com.ssafy.gumibom.domain.record.dto.PersonalRecordDto;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonalPamphletService {

    private final PersonalPamphletRepository pPamphletRepository;
    private final UserRepository userRepository;

    // 개인 팜플렛 생성
    @Transactional
    public Long makePamphlet(MakePersonalPamphletRequestDto makePPReqDto) {

        User user = userRepository.findOne(makePPReqDto.getUserId());
        PersonalPamphlet pPamphlet = PersonalPamphlet.createPersonalPamphlet(user, makePPReqDto.getTitle());

        return pPamphletRepository.save(pPamphlet);
    }

    // 팜플렛 아이디로 개인 팜플렛 조회
    public PersonalPamphlet selectPamphletById(Long pamphletId) {
        return pPamphletRepository.findByPamphletId(pamphletId);
    }

    // 유저 아이디로 개인 팜플렛 목록 조회
    public List<PersonalPamphletDto> selectPamphletByUserId(Long userId) {
        List<PersonalPamphlet> myPamphlets = pPamphletRepository.findByUserId(userId);
        List<PersonalPamphletDto> myPamphletsDto = myPamphlets.stream()
                .map(p -> new PersonalPamphletDto(p))
                .collect(Collectors.toList());

        return myPamphletsDto;
    }

    // 사용자의 개인 팜플렛을 제외한 모든 개인 팜플렛 조회
    public List<PersonalPamphletDto> selectAllPamphlet(Long userId) {
        List<PersonalPamphlet> myPersonalPamphlet = pPamphletRepository.findByUserId(userId);
        List<PersonalPamphlet> allPersonalPamphlet = pPamphletRepository.findAll();

        for(PersonalPamphlet myPamphlet: myPersonalPamphlet) {
            allPersonalPamphlet.remove(myPamphlet);
        }

        List<PersonalPamphletDto> allPersonPamphletDto = allPersonalPamphlet.stream()
                .map(p -> new PersonalPamphletDto(p))
                .collect(Collectors.toList());

        return allPersonPamphletDto;
    }
}
