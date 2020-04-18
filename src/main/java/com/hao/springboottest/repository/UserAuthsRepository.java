package com.hao.springboottest.repository;

import com.hao.springboottest.entity.UserAuths;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthsRepository extends JpaRepository<UserAuths,Integer> {
    UserAuths findByIdentifier(String identifier);
}
