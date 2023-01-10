package com.noodles.refer.auth.utils;

import com.noodles.refer.auth.constant.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Noodles
 * @since 2022-11-17 16:21
 */
@Slf4j
public class JwtTokenUtils {

    /*** 生成随机密钥 */
    private static final byte[] API_KEY_SECRET_BYTES = DatatypeConverter.parseBase64Binary(SecurityConstant.JWT_SECRET_KEY);
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(API_KEY_SECRET_BYTES);

    public static String createToken(String username, Long id, List<String> roles) {
        Date createDate = new Date();
        Date expireDate = new Date(createDate.getTime() + SecurityConstant.EXPIRATION * 1000);

        String token = Jwts.builder()
                .setHeaderParam("type", SecurityConstant.TOKEN_TYPE)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .claim(SecurityConstant.ROLE_CLAIMS, String.join(",", roles))
                .setId(String.valueOf(id))
                .setIssuedAt(createDate)
                .setSubject(username)
                .setExpiration(expireDate)
                .compact();
        // 添加 token 前缀 "Bearer ";
        return SecurityConstant.TOKEN_PREFIX + token;
    }

    public static String getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.getId();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = getClaims(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        String username = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(username, token, authorities);
    }

    private static List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        String role = (String) claims.get(SecurityConstant.ROLE_CLAIMS);
        return Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}
