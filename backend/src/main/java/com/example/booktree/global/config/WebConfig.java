package com.example.booktree.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //"http://localhost:3000",
                .allowedOrigins( "http://seb41-main-022.s3-website.ap-northeast-2.amazonaws.com")
                .allowedMethods("*")
//                .exposedHeaders("Authorization", "Refresh")
//                .allowCredentials(false)
                .allowCredentials(true) // 쿠키 정책 허용
                .maxAge(3000);
    }
}
