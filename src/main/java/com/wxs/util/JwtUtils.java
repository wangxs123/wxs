package com.wxs.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt
 *
 * @author: wxs
 * @date: 2019/05/29 10:28
 */
public class JwtUtils {
    /**
     * 过期时间（6小时）
     */
    private static final long EXPIRE_TIME = 6 * 60 * 60 * 1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "b2f0aeeb51b14836be6fe72cf97374c0";

    /**
     * 生成签名
     *
     * @param userName
     * @param userId
     * @return
     */
    public static String sign(String userName, Long userId) {
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> header = new HashMap<>();
            header.put("type", "JWT");
            header.put("algorithm", "HMAC256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userName", userName)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
