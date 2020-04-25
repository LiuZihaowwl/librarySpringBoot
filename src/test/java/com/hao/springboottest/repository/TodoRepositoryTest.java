package com.hao.springboottest.repository;

import com.hao.springboottest.entity.Todo;
import com.hao.springboottest.utils.RetCode;
import com.hao.springboottest.utils.RetResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;
    @Test
    void addTodo(){
        Todo todo = new Todo();
        todo.setUId(1066);
        todo.setTodolist("上网，睡觉");
        todoRepository.save(todo);
    }
    @Test
    void findAll(){
        Todo todo = new Todo();
        todo = todoRepository.findByuId(1057);
        RetResult retResult = new RetResult();
        retResult.setCode(RetCode.SUCCESS.getCode());
        retResult.setData(todo.toString());
        System.out.println(todo.toString());
        System.out.println(retResult);
        System.out.println(retResult.toString());
    }
    @Test
    void update(){
        todoRepository.updateByUID("我来上网，睡觉",1057);
    }

}
