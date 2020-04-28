package com.hao.springboottest.controller;

import com.hao.springboottest.entity.Book;
import com.hao.springboottest.repository.BookRepository;
import com.hao.springboottest.utils.RedisService;
import com.hao.springboottest.utils.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/findAll/{page}/{size}")
    public Page<Book> findAll(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@RequestParam(value = "id")String uid){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Book> page1 = bookRepository.findAllByuId(Integer.valueOf(uid),pageRequest);
        return page1;
    }
    @PostMapping("/save")
    public String save(@RequestBody Map<String,String> map){
        Book book = new Book();
        book.setName(map.get("name"));
        book.setAuthor(map.get("author"));
        book.setUId(Integer.valueOf(map.get("uId")));
        System.out.println(book);
        System.out.println(book.getName());
        System.out.println(book.getUId());
        System.out.println(book.getAuthor());
        Book result = bookRepository.save(book);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }
    @GetMapping("/findById/{id}")
    public Book findById(@PathVariable("id")Integer id){
        ValueOperations operations = redisTemplate.opsForValue();
        if(operations.get("BookId"+id)!=null) {
            System.out.println("进入了" + operations.get("BookId" + id));
            return (Book) operations.get("BookId" + id);
        }
        else
        {
            operations.set("BookId"+id,bookRepository.findById(id).get());
            System.out.println("进入了这里"+operations.get("BookId"+id));
            return (Book)operations.get("BookId"+id);
        }
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

