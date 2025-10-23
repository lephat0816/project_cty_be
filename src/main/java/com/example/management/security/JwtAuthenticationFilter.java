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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTPリクエストごとにJWTトークンを検証し、
 * 認証情報をSpring Securityのコンテキストに設定するフィルタークラス。
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JWTUtil jwtUtil;
    /**
     * コンストラクタ
     * 
     * @param jwtUtil JWT操作用ユーティリティ
     */
    public JwtAuthenticationFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * フィルター処理を実行。
     * リクエストからJWTを抽出し、有効な場合は認証情報を設定。
     *
     * @param request HTTPリクエスト
     * @param response HTTPレスポンス
     * @param filterChain フィルター連鎖
     * @throws ServletException サーブレット例外
     * @throws IOException 入出力例外
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // リクエストからトークンを抽出
        String token = extractBearerToken(request);
        // トークンが存在し、有効な場合に認証情報を設定
        if (token != null && jwtUtil.validateToken(token, jwtUtil.extractUsername(token))) {
            // トークンからクレーム（役割やその他の情報）を抽出
            Claims claims = jwtUtil.extractAllClaims(token);
            logger.info("Filter試行: claims: {}",claims);
            // クレームから役割リストを取得(admin/user)。なければ空リストをセット
            // List<String> roles = claims.get("authorities", List.class);
            List<?> rawRoles = claims.get("authorities", List.class);
            List<String> roles = rawRoles != null ? rawRoles.stream()
                                                            .map(Object::toString)
                                                            .collect(Collectors.toList())
                                                : List.of();
            // if (roles == null) {
            //     roles = List.of();
            // }
            // 役割リストを SimpleGrantedAuthority に変換
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            authorities.forEach(a -> logger.info("authorities: {}", a.getAuthority()));
      
            // 認証オブジェクトを作成し、セキュリティコンテキストに設定
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        // 次のフィルターにリクエストを渡す
        filterChain.doFilter(request, response);
    }

    /**
     * HTTPリクエストからBearerトークンを抽出。
     *
     * @param request HTTPリクエスト
     * @return トークン文字列。存在しない場合はnull
     */
    private String extractBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // "Bearer " の部分を取り除いてトークンだけを返す
            return bearerToken.substring(7);
        }
        return null;
    }
}
