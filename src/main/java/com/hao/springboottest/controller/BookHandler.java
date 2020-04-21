package com.hao.springboottest.controller;

import com.hao.springboottest.entity.Book;
import com.hao.springboottest.repository.BookRepository;
import com.hao.springboottest.utils.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/findAll/{page}/{size}")
    public Page<Book> findAll(@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Book> page1 = bookRepository.findAll(pageRequest);
        return page1;
    }
    @PostMapping("/save")
    public String save(@RequestBody Book book){
        Book result = bookRepository.save(book);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }
    @GetMapping("/findById/{id}")
    public Book findById(@PathVariable("id")Integer id){
        return bookRepository.findById(id).get();
    }
    @PutMapping("/update")
    public String update(@RequestBody Book book){
        Book result = bookRepository.save(book);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id")Integer id){
        bookRepository.deleteById(id);
    }
}

