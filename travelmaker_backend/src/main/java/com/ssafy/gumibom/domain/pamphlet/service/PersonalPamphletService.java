package com.ssafy.gumibom.domain.pamphlet.service;

import com.ssafy.gumibom.domain.pamphlet.repository.PersonalPamphletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonalPamphletService extends PamphletService {

    private final PersonalPamphletRepository pamphletRepository;

    @Override
    public void makePamphlet() {

    }
}
