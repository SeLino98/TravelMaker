package com.ssafy.gumibom.domain.pamphlet.service;


import com.ssafy.gumibom.domain.pamphlet.repository.PamphletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public abstract class PamphletService {


}
