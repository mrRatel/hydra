package com.ratel.hydra.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

/**
 * @author com.ratel
 * @date 2020/3/16
 */
public class JwtTokenUtil {
    /**
     * 加密算法
     **/
    private volatile static SignatureAlgorithm signatureAlgorithm;
    /**
     * 秘钥
     **/
    private volatile static Key secretKey;

    /**
     * @param payLoad
     * @return java.lang.String
     * @Description 生成token
     * @Author com.ratel
     * @Date 2020/3/16
     **/
    public static String generatorToken(Map<String, Object> payLoad) {
//        JwtBuilder builder = Jwts.builder()
//                .setId(id)
//                .setSubject(subject)   // 主题
//                .setIssuer("user")     // 签发者
//                .setIssuedAt(now)      // 签发时间
//                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        return Jwts.builder()
                .setClaims(payLoad)
//                .setPayload(JSON.toJSONString(payLoad))
                .signWith(getSignatureAlgorithm(), "4ea88cecb57c4eeea8f382da613799a3")
                .compact();
    }

    /**
     * @Description 解析token
     * @Author      com.ratel
     * @Date        2020/3/16
     * @param       token
     * @return      io.jsonwebtoken.Claims
     **/
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
    }

    private static Key getSecretKey() {
        if (secretKey != null) {
            return secretKey;
        }
        synchronized (JwtTokenUtil.class) {
            if (secretKey != null) {
                return secretKey;
            }
        }
        //秘钥
        byte[] encodedKey = DatatypeConverter.parseBase64Binary("4ea88cecb57c4eeea8f382da613799a3");
        secretKey = new SecretKeySpec(encodedKey, 0,encodedKey.length,"AES");
        return secretKey;
    }

    private static SignatureAlgorithm getSignatureAlgorithm(){
        if (signatureAlgorithm != null) {
            return signatureAlgorithm;
        }
        synchronized (JwtTokenUtil.class) {
            if (signatureAlgorithm != null) {
                return signatureAlgorithm;
            }
            //加密算法
            signatureAlgorithm = SignatureAlgorithm.HS512;
            return signatureAlgorithm;
        }
    }
}
