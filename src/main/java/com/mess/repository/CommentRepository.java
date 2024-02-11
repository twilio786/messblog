package com.mess.repository;

import com.mess.entity.CommentPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentPost,Long> {
    List<CommentPost> findByMessPostId(long messPostId);


}
