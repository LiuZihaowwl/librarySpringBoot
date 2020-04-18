package com.hao.springboottest.controller;


import com.hao.springboottest.entity.User;
import com.hao.springboottest.entity.UserAuths;
import com.hao.springboottest.repository.UserAuthsRepository;
import com.hao.springboottest.repository.UserRepository;
import com.hao.springboottest.utils.PasswordStore;
import com.hao.springboottest.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthsRepository userAuthsRepository;
    @PostMapping("/register")
        public String save(@RequestBody Map<String,String> map) throws ParseException, InvalidKeySpecException, NoSuchAlgorithmException {
        User user = new User();
        user.setUName(map.get("uName"));
        user.setUSex(map.get("uSex"));
        Date uBirthday = TimeUtils.StringToDate(map.get("uBirthday"));
        user.setUBirthday(uBirthday);
        user.setUSignature("开卷有益");
        user.setUAvatar("F:/");
        User userResult = userRepository.save(user);
        UserAuths userAuths = new UserAuths();
        String[] pasAndSalt = new String[2];
        pasAndSalt = PasswordStore.exchange(map.get("pass"));
        userAuths.setSalt(pasAndSalt[0]);
        userAuths.setCredential(pasAndSalt[1]);
        userAuths.setIdentityType("email");
        userAuths.setIdentifier(map.get("email"));
        userAuths.setUId(userResult.getUId());
        System.out.println(userAuths.toString());
        userAuthsRepository.save(userAuths);
        return "success";
    }
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> map) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String user_email = map.get("email");
        String user_pas = map.get("pass");
        UserAuths userAuths = new UserAuths();
        userAuths = userAuthsRepository.findByIdentifier(user_email);
        if(userAuths != null){
            String salt = userAuths.getSalt();
            String pas = userAuths.getCredential();
            if(PasswordStore.authenticate(user_pas,pas,salt)){
                return "SUCCESS";
            }
        }
        return "false";

    }
}