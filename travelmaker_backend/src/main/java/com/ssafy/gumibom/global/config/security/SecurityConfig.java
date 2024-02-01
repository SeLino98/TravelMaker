package com.ssafy.gumibom.global.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.gumibom.global.jwt.JWTFilter;
import com.ssafy.gumibom.global.jwt.JWTUtil;
import com.ssafy.gumibom.global.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;


    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {

        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        //프론트 단에서 보낼 서버의 주소를 허용
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        //허용할 method를 *로 함으로써 get, post, put, delete 등 모든 method를 허용
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        //프론트단으로 header를 보낼때 Authorization에 jwt를 넣어서 보낼 것 이므로 Authorization을 허용한다
                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                })));
        http
                .csrf((auth)->auth.disable());
        http
                .formLogin((auth)->auth.disable());
        http
                .httpBasic((auth)->auth.disable());

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/login", "/", "/join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        //JWTFilter 등록
        //JWTFilter는 LoginFilter 이전에 위치하여 먼저 들린다
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        //LoginFilter()를 UsernamePasswordAuthenticationFilter 위치에 추가시킨다(addFilterAt).
        /*LoginFilter()를 UsernamePasswordAuthenticationFilter로 대체하는 이유는 formLogin을 disable 시켜놔서
        username과 password로 인가작업하는 필터를 사용할 수 없게 되서
        우리가 UsernamePasswordAuthenticationFilter를 상속받아 커스텀한 LoginFilter를 만들어서 사용
         */
        /*
        LoginFilter는 authenticationManager를 인자로 받아야 하고
        authenticationManager또한 인자로 AuthenticationConfiguration를 받아와야한다
        그래서 상단 필드에 생성자 주입으로 해당 요소들을 불러온다
         */
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //세션 설정
        //jwt에서는 세션을 stateless상태로 설정해야한다
        http
                .sessionManagement((session)->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
