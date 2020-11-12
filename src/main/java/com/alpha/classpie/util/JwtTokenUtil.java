package com.alpha.classpie.util;


import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

/**
 * @author 杨能
 * @create 2020/11/11
 */
public class JwtTokenUtil {
    // Token请求头
    public static final String TOKEN_HEADER = "Authorization";
    // Token前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 签名主题
    public static final String SUBJECT = "alpha";
    // 过期时间
    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;
    // 应用密钥
    public static final String APPSECRET_KEY = UUID.randomUUID().toString();
    // 角色权限声明
    private static final String ROLE_CLAIMS = "role";
    // UserId声明
    private static final String USERID="userId";
    // Username
    private static final String USERNAME="username";

     static Gson gson = new Gson();

    /**
     * 生成Token
     */
    public static String createToken(String username,int userId, Collection<? extends GrantedAuthority> roles) {
        Map<String,Object> map = new HashMap<>();
        map.put(USERID,userId);
        map.put(ROLE_CLAIMS, roles);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    /**
     * 校验Token
     */
    public static Claims checkJWT(String token) {
        try {
            return Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从Token中获取username
     */
    public static String getUsername(String token){
        Claims claims = checkJWT(token);
        assert claims != null;
        return claims.getSubject();
    }

    public static Integer getUserId(String token){
        return Objects.requireNonNull(checkJWT(token)).get(USERID,Integer.class);
    }

    /**
     * 从Token中获取用户角色
     */
    public static Collection<? extends GrantedAuthority> getUserRoles(String token){
        Claims claims = checkJWT(token);
        List<? extends GrantedAuthority> list=new ArrayList<>();
        return claims.get(ROLE_CLAIMS,list.getClass());
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token){
        Claims claims = checkJWT(token);
        assert claims != null;
        return claims.getExpiration().before(new Date());
    }
}
