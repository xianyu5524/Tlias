package com.tliasservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String base64SignKey = Encoders.BASE64.encode(Jwts.SIG.HS256.key().build().getEncoded()); // Base64 编码的密钥
    private static Long expire = 43200000L;

    // 将 Base64 字符串解码为安全的 SecretKey
    private static SecretKey getSignKey() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(base64SignKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成 JWT 令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims) // 直接设置 claims
                .expiration(new Date(System.currentTimeMillis() + expire))
                .signWith(getSignKey()) // 自动推断算法
                .compact();
    }

    /**
     * 解析 JWT 令牌
     * @param jwt JWT 令牌
     * @return JWT 第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .verifyWith(getSignKey()) // 设置验证密钥
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}