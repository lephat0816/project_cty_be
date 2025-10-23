package com.example.management.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.management.security.JwtAuthenticationFilter;
import com.example.management.util.JWTUtil;

/*
 * Spring Securityの設定クラス
 * このクラスはアプリケーションのセキュリティポリシー、
 * CORS設定、JWT認証フィルターを定義
 */

@Configuration
@EnableMethodSecurity

public class SecurityConfig {
    @Value("${cors.allowed-origins}")
    private String allowedOrigins;
    @Autowired
    WebConfig webConfig;
    /**
     * パスワードエンコーダーをBeanとして登録。
     * BCryptアルゴリズムを使用してパスワードをハッシュ化。
     * @return パスワードエンコーダー
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final JWTUtil jwtUtil;
    /**
     * コンストラクタ。JWTユーティリティを注入
     * @param jwtUtil TWTトークンの生成と検証を行うユーティリティ
     */
    public SecurityConfig(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    /**
     * Spring Securityのフィルターチェーンを構築。
     * JWT認証フィルターを追加し、エンドポイントごとのアクセス制御を設定。
     * @param http HttpSecurity Objet
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("admin")
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * CORS(Cross-Origin Resource Sharing)の設定を定義。
     * フロントエンド("http://localhost:3000")からのアクセスを許可。
     * @returnCorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(allowedOrigins));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
