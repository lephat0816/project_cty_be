package com.example.management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;;;
/**
 * Web全体の設定クラス。
 * このクラスではSpring MVCレベルでのCORSの設定
 * @RestControllerや@Controllerで処理されるリクエストに適用される。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * フロントエンド（http://localhost:3000）からのリクエストを許可し、
     * 特定のHTTPメソッドとヘッダーを許可。
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 全てのパスに対してCORSを適用
                .allowedOrigins("http://localhost:3000") // 許可するオリジン（フロントエンドのURL）
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS") // 許可するHTTPメソッド
                .allowedHeaders("*")  // 許可するリクエストヘッダー（全て許可）
                .allowCredentials(true); // クッキーや認証情報を許可
    }
}
