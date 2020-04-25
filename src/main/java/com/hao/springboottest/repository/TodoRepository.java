package com.hao.springboottest.repository;

import com.hao.springboottest.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    Todo findByuId(Integer uId);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Todo todo set todo.todolist=?1 where todo.uId=?2")
    int updateByUID(String todolist,Integer uId);
}
