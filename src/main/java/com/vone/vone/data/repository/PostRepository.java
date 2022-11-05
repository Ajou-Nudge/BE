package com.vone.vone.data.repository;

import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByVerifierId(String verifierId);
}
