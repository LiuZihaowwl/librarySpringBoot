package com.hao.springboottest.repository;

import com.hao.springboottest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByuId(Integer uId);
}
