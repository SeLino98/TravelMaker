package com.ssafy.gumibom.global.jwt;

import com.ssafy.gumibom.domain.user.dto.CustomUserDetails;
import com.ssafy.gumibom.domain.user.entity.Role;
import com.ssafy.gumibom.domain.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    public JWTFilter(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authorization 헤더를 찾음
        String authorization= request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        System.out.println("authorization now");
        //Bearer 부분 제거 후 순수 토큰만 획득
        //공백으로 나누어 리스트로 한다음 1번 인덱스 값을 token에 저장하면 Bearer를 제거한 값만 남게됨
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {

            System.out.println("token expired");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        String roleStr = jwtUtil.getRole(token);

        Role role;
        try {
            role = Role.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            // 유효하지 않은 role 값에 대한 예외 처리
            // 여기서는 예시로 간단히 처리했지만, 실제로는 더 구체적인 예외 처리가 필요할 수 있습니다.
            throw new RuntimeException("Invalid role: " + roleStr);
        }


        //user를 생성하여 값 set
        User user = new User(username, "temppasword", role);

   ;     //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        //스프링 시큐리티 인증 토큰 생성
        //customUserDetails를 담고 있는 token
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        //세션에 사용자를 등록했기 때문에 특정한 경로에 접근을 할 수 있도록 한다
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);


    }
}
