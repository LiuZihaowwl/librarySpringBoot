package com.hao.springboottest.controller;


import com.alibaba.fastjson.JSONObject;
import com.hao.springboottest.entity.User;
import com.hao.springboottest.entity.UserAuths;
import com.hao.springboottest.repository.UserAuthsRepository;
import com.hao.springboottest.repository.UserRepository;
import com.hao.springboottest.utils.*;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    TokenService tokenService;
    @PostMapping("/register")
    public RetResult save(@RequestBody Map<String,String> map) throws ParseException, InvalidKeySpecException, NoSuchAlgorithmException {
        String email = map.get("email");
        RetResult retResult = new RetResult();
        if(userAuthsRepository.findByIdentifier(email) == null){
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
            userAuths.setIdentifier(email);
            userAuths.setUId(userResult.getUId());
            userAuthsRepository.save(userAuths);
            retResult.setCode(RetCode.SUCCESS.getCode());
            retResult.setMsg("注册成功");
            return retResult;
        }
        retResult.setCode(RetCode.FAIL.getCode());
        retResult.setMsg("此邮箱已注册");
        return retResult;
    }
    @PostMapping("/login")
    public RetResult login(@RequestBody Map<String,String> map) throws InvalidKeySpecException, NoSuchAlgorithmException {
        JSONObject jsonObject = new JSONObject();
        String user_email = map.get("email");
        String user_pas = map.get("pass");
        User user = new User();
        UserAuths userAuths = new UserAuths();
        RetResult retResult = new RetResult();
        userAuths = userAuthsRepository.findByIdentifier(user_email);
        if(userAuths != null){
            user = userRepository.findByuId(userAuths.getUId());
            String salt = userAuths.getSalt();
            String pas = userAuths.getCredential();
            if(PasswordStore.authenticate(user_pas,pas,salt)){
                String token = tokenService.getToken(user,userAuths);
                jsonObject.put("token",token);
                jsonObject.put("user",user);
                System.out.println("登录成功");
                retResult.setCode(RetCode.SUCCESS.getCode());
                retResult.setData(jsonObject);
                return retResult;
            }
        }
        retResult.setCode(RetCode.FALSE.getCode());
        retResult.setMsg("账号或者密码输入错误");
        return retResult;
    }
    @PutMapping("/updateUserMessage")
    public RetResult updateUserMessage(@RequestBody Map<String,String> map) throws ParseException {
        RetResult retResult = new RetResult();
        String uid = map.get("uid");
        String Date = map.get("uDate");
        String userName = map.get("uName");
        String uSex = map.get("uSex");
        String uSignature = map.get("uSignature");
        java.sql.Date date = TimeUtils.StringToDate(Date);
        userRepository.updateByuId(userName,uSex,date,uSignature,Integer.valueOf(uid));
        retResult.setCode(RetCode.SUCCESS.getCode());
        retResult.setMsg("修改成功");
        return retResult;
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
