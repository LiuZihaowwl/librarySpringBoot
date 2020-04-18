package com.hao.springboottest;

import com.hao.springboottest.entity.Book;
import com.hao.springboottest.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

@SpringBootTest
class SpringboottestApplicationTests {
    @Autowired
    private BookRepository bookRepository;
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
}
