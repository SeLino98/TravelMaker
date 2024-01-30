package com.ssafy.gumibom.domain.user.service;

import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public Long join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user){
        User findUser = userRepository.findByUsername(user.getUsername());
        if(findUser != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


}
