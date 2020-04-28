package com.hao.springboottest;

import com.hao.springboottest.entity.Book;
import com.hao.springboottest.repository.BookRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


import javax.transaction.Transactional;

@SpringBootTest
@EnableCaching
class SpringboottestApplicationTests {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Test
    void contextLoads() {
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<Book> page = bookRepository.findAll(pageRequest);
        int i = 0;
    }
    @Test
    void save(){
        Book book = new Book();
        book.setName("test666");
        book.setAuthor("test666");
        Book book1 = bookRepository.save(book);
        System.out.println(book1);
    }
    @Test
    void findById(){
        Book book = bookRepository.findById(1).get();
        System.out.println(book);
    }
    @Test
    void update(){
        Book book = new Book();
        book.setId(122);
        book.setName("测试测试");
        Book book1 = bookRepository.save(book);
        System.out.println(book1);
    }
    @Test
    void deleteById(){
        bookRepository.deleteById(122);
    }
    @Test
    public void RedisTest(){
        ValueOperations<String ,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("Hello","World");
        String value = valueOperations.get("Hello");
        System.out.println(value);
    }
}
