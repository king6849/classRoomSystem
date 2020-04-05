package com.myteam.classroomsystem.scas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value="prototype")
public class Token {

    final long exptime = 60 * 60 * 24 * 1000;
    final String secret = "king";
    final String issuer = "king";

    //    生产Token
    public String generateToken(String sessionkey, String openid) {
        //创建头部
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        //构建密钥
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //生成token
        String token = JWT.create()
                .withHeader(header)
                //签发人
                .withIssuer(issuer)
//                //接受人
//                .withAudience()
                //附带信息
                .withClaim("session_key", sessionkey)
                .withClaim("openid", openid)
                //签发时间
                .withIssuedAt(new Date())
                //过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + exptime))
                .sign(algorithm);
        return token;
    }

    //解析Token
    public DecodedJWT parseToken(String token) {
        //构造密钥
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }

}
