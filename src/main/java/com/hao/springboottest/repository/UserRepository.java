package com.hao.springboottest.repository;

import com.hao.springboottest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByuId(Integer uId);
    @Transactional
    @Modifying
    @Query("update User u set u.uName=?1,u.uSex=?2,u.uBirthday=?3,u.uAvatar=?4 where u.uId=?5")
    void updateByuId(String uName, String uSex, Date date,String uAvatar,Integer uId);
}
