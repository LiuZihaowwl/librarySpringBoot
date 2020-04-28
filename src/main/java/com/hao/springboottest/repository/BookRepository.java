package com.hao.springboottest.repository;

import com.hao.springboottest.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    public Page<Book> findAllByuId(Integer uId, Pageable pageable);
}
