package com.ssafy.gumibom.domain.pamphlet.service;

import com.ssafy.gumibom.domain.pamphlet.dto.request.MakePersonalPamphletRequestDto;
import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import com.ssafy.gumibom.domain.pamphlet.repository.PersonalPamphletRepository;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
