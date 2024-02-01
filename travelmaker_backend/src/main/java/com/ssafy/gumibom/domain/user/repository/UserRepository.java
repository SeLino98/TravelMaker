package com.ssafy.gumibom.domain.user.repository;

import com.ssafy.gumibom.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 아이디와 비밀번호로 사용자 찾기 (로그인 기능에 사용)
    Optional<User> findByUsernameAndPassword(String username, String password);

    // 사용자 이름으로 사용자 찾기 (프로필 정보 조회에 사용)
    Optional<User> findByUsername(String username);


    Optional<User> findByNickname(String nickname);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
    boolean existsByPhone(String phone);

}
