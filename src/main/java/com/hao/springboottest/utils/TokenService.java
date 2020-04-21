package com.hao.springboottest.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hao.springboottest.entity.User;
import com.hao.springboottest.entity.UserAuths;
import org.springframework.stereotype.Service;

@Service("TokenService")
public class TokenService {
    public String getToken(User user, UserAuths userAuths){
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getUId()))//将uId保存在token里,这里把数字转化为字符串传入参数
        .sign(Algorithm.HMAC256(userAuths.getCredential()));
        return token;
    }
}
