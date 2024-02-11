package com.mess.repository;

import com.mess.entity.MessPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessRepository extends JpaRepository<MessPost,Long> {
}
