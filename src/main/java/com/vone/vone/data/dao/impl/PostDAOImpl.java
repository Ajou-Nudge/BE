package com.vone.vone.data.dao.impl;

import com.vone.vone.data.dao.PostDAO;
import com.vone.vone.data.entity.Post;
import com.vone.vone.data.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PostDAOImpl implements PostDAO {

    private final PostRepository postRepository;

    @Autowired
    public PostDAOImpl (PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post insertPost(Post post){
        Post savedPost = postRepository.save(post);

        return savedPost;
    }

    @Override
    public Post selectPost(Long number){
        Post selected = postRepository.getById(number);

        return selected;
    }

    @Override
    public List<Post> selectAllPost(){
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public List<Post> selectAllPostByVerifier(String verifierId){
        List<Post> posts = postRepository.findByVerifierId(verifierId);
        return posts;
    }
    @Override
    public Post updatePostnTitle(Long number, String title) throws Exception{
        Optional<Post> selectedPost = postRepository.findById(number);

        Post updatedPost;
        if (selectedPost.isPresent()) {
            Post post = selectedPost.get();

            post.setTitle(title);
            post.setUpdatedAt(LocalDateTime.now());

            updatedPost = postRepository.save(post);
        } else {
            throw new Exception();
        }

        return updatedPost;
    }

    @Override
    public void deletePost(Long number) throws Exception {
        Optional<Post> selectedPost = postRepository.findById(number);

        if ( selectedPost.isPresent() ){
            Post post = selectedPost.get();

            postRepository.delete(post);
        } else {
            throw new Exception();
        }
    }
}
