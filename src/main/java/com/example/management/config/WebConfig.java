package com.example.management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;;;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 全てのパスに対してCORSを適用
                .allowedOrigins("http://localhost:3000") // 許可するオリジン（フロントエンドのURL）
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 許可するHTTPメソッド
                .allowedHeaders("*")  // 許可するリクエストヘッダー（全て許可）
                .allowCredentials(true); // クッキーや認証情報を許可
    }
}
