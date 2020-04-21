package com.hao.springboottest.repository;

import com.hao.springboottest.entity.UserAuths;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthsRepository extends JpaRepository<UserAuths,Integer> {
    UserAuths findByIdentifier(String identifier);
    UserAuths findByuId(Integer uId); //JAP有驼峰命名规则，字段为uId，若设置为findByUId是不可行的，设置为findByuId可以
}
