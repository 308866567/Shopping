package com.xbd.mall.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtToken {

    //默认秘钥
    private static final String DEFAULT_SECRET="springcloudalibaba";

    /***
     * 生成令牌
     * @param dataMap
     * @return
     */
    public static String createToken(Map<String,Object> dataMap){
        return createToken(dataMap,null);
    }

    /**
     * 生成令牌
     * @param dataMap 数据
     * @param secret 密钥,没有就使用默认密钥
     * @return
     */
    public static String createToken(Map<String,Object> dataMap,String secret){
        //秘钥为空就采用默认秘钥
        if(StringUtils.isEmpty(secret)){
            secret = DEFAULT_SECRET;
        }
        //创建令牌操作算法
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //创建令牌
        return JWT.create()
                .withClaim("body",dataMap)
                .withIssuer("GP")            //JWT签发者
                .withSubject("JWT令牌")       //主题
                .withAudience("member")      //接收JWT的一方
                .withExpiresAt(new Date(System.currentTimeMillis()+3600000))    //过期时间,ms
                .withNotBefore(new Date(System.currentTimeMillis()))      //指定时间之前JWT令牌是不可用的
                .withIssuedAt(new Date())    //JWT签发时间
                .withJWTId(UUID.randomUUID().toString().replace("-","")) // jwt唯一标识
                .sign(algorithm);
    }

    /***
     * 解析令牌
     * @param token
     * @return
     */
    public static Map<String,Object> parseToken(String token){
        return parseToken(token,null);
    }

    /***
     * 令牌校验并解析
     * @param token
     * @return
     */
    public static Map<String,Object> parseToken(String token,String secret){
        //秘钥为空就采用默认秘钥
        if(StringUtils.isEmpty(secret)){
            secret = DEFAULT_SECRET;
        }
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("body").as(Map.class);
    }

    /***
     * 令牌校验解析测试
     * @param args
     */
    public static void main(String[] args) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("name","zhangsan");
        dataMap.put("age","26");
        dataMap.put("address","深圳市");

        Map<String,Object> headerMap = new HashMap<String,Object>();
        headerMap.put("version","v1.0");
        headerMap.put("mysql","5.7");

        //创建令牌
        String token = createToken(dataMap);
        System.out.println(token);

        //解析令牌
        Map<String,Object> resultMap =parseToken(token);
        System.out.println(resultMap);
    }
}
