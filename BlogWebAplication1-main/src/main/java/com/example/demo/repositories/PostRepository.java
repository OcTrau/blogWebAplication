package com.example.demo.repositories;

import com.example.demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Chua cac thong tin va chuc năng tương tác với database

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



}
