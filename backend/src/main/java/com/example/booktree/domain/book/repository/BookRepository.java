package com.example.booktree.domain.book.repository;

import com.example.booktree.domain.book.entity.Book;
import com.example.booktree.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findAllByUser(User user, Pageable pageable);


}
