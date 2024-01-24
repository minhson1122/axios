package com.example.axios.repository;

import com.example.axios.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long > {
    List<Post> findAllByOrderByCreateAtDesc();
    List<Post> findByStatus(String status);

    List<Post> findByStatusIn(List<String> statuses);
    List<Post> findByTitleContainingOrContentContaining(String title, String content);
}
