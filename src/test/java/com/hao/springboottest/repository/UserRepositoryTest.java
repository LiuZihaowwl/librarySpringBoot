package com.hao.springboottest.repository;

import com.hao.springboottest.entity.User;
import com.hao.springboottest.entity.UserAuths;
import com.hao.springboottest.utils.TimeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserAuthsRepository userAuthsRepository;
    @Test
    void addUser(){
        User user = new User();
        Date date = new Date();
        java.sql.Date d = new java.sql.Date(date.getTime());
        System.out.println(d);
        user.setUBirthday(d);
        user.setUAvatar("F:/");
        user.setUName("hao");
        user.setUSex("男");
        user.setUSignature("niu");
        User user1 = userRepository.save(user);
        System.out.println(user1);

        UserAuths userAuths = new UserAuths();

        userAuths.setCredential("666666");
        userAuths.setIdentityType("email");
        userAuths.setIdentifier("364");
        userAuths.setUId(user1.getUId());
        userAuthsRepository.save(userAuths);
    }
    @Test
    void findUser(){
        User user = new User();
        user = userRepository.findByuId(Integer.valueOf("1057"));

        System.out.println(user.toString());
    }


}
