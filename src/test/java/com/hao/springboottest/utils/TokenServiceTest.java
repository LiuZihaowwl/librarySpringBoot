package com.hao.springboottest.utils;

import com.hao.springboottest.entity.User;
import com.hao.springboottest.entity.UserAuths;
import com.hao.springboottest.repository.UserAuthsRepository;
import com.hao.springboottest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TokenServiceTest {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAuthsRepository userAuthsRepository;
    @Test
    void testIt(){
        String token = "";
        User user = userRepository.findByuId(1057);
        UserAuths userAuths = userAuthsRepository.findByuId(1057);
        token = tokenService.getToken(user,userAuths);
        System.out.println(token);

    }

}
