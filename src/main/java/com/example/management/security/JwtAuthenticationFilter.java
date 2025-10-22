package com.example.management.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.management.util.JWTUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // JWTUtilのインスタンスを注入
    private final JWTUtil jwtUtil;

    public JwtAuthenticationFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // リクエストからトークンを抽出
        String token = extractBearerToken(request);
        // トークンが存在し、有効な場合に認証情報を設定
        if (token != null && jwtUtil.validateToken(token, jwtUtil.extractUsername(token))) {
            // トークンからクレーム（役割やその他の情報）を抽出
            Claims claims = jwtUtil.extractAllClaims(token);
            System.out.println("day la claims" + claims);
            // クレームから役割リストを取得(admin/user)。なければ空リストをセット
            List<String> roles = claims.get("authorities", List.class);
            if (roles == null) {
                
                roles = List.of();
            }
            // 役割リストを SimpleGrantedAuthority に変換
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
                    authorities.forEach(a -> System.out.println("Granted authority: " + a.getAuthority()));

            // 認証オブジェクトを作成し、セキュリティコンテキストに設定
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        // 次のフィルターにリクエストを渡す
        filterChain.doFilter(request, response);
    }
    // HTTP リクエストから Bearer トークンを抽出するメソッド
    private String extractBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // "Bearer " の部分を取り除いてトークンだけを返す
            return bearerToken.substring(7);
        }
        return null;
    }
}
