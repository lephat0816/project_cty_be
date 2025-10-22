package com.example.management.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {
    // トークン署名用の秘密鍵
    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    // トークン有効期限（10時間）
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    /**
     * JWTトークンを生成する
     * 
     * @param username ユーザー名
     * @param userId   ユーザーID
     * @return 生成したJWTトークン
     */
    public String generateToken(String username, Long userId, String auth) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId); // tokenにユーザーIDを保存
        claims.put("authorities", List.of("ROLE_" + auth));
        String token = Jwts.builder()
                .claims(claims) // カスタムクレームを追加
                .subject(username) // subjectにusernameを設定
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();

        return token;

    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    public Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        System.out.println("Extracted: " + extractedUsername);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // トークンから全てのクレームを取得
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
}