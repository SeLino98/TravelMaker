package com.ssafy.gumibom.global.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    // 허용할 프론트단 url로 교체해야함
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");
    }
}
