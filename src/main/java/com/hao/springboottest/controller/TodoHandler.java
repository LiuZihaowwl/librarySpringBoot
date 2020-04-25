package com.hao.springboottest.controller;

import com.hao.springboottest.entity.Todo;
import com.hao.springboottest.repository.TodoRepository;
import com.hao.springboottest.utils.RetCode;
import com.hao.springboottest.utils.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TodoHandler {
    @Autowired
    private TodoRepository todoRepository;
//    @PostMapping("/addTodo")
//    public String saveTodo(@RequestBody Map<String,String> map){
//
//    }
    @GetMapping("/findAll")
    public RetResult todoFindAll(@RequestParam(value = "uid")String uid){
        Todo todo = new Todo();
        todo = todoRepository.findByuId(Integer.valueOf(uid));
        String todoList = todo.getTodolist();
        RetResult retResult = new RetResult();
        retResult.setCode(RetCode.SUCCESS.getCode());
        retResult.setData(todo.getTodolist());
        return retResult;
    }
    @PostMapping("/update")
    public RetResult update(@RequestParam(value = "uid")String uid,@RequestParam(value = "todoList")String todoList){
        RetResult retResult = new RetResult() ;
        System.out.println(todoList);
        todoRepository.updateByUID(todoList, Integer.valueOf(uid));
        retResult.setCode(RetCode.SUCCESS.getCode());
        System.out.println(todoRepository.findByuId(Integer.valueOf(uid)).getTodolist());
        return retResult;
    }
}
