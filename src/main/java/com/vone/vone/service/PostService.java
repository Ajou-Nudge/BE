package com.vone.vone.service;

import com.vone.vone.data.dto.PostDto;
import com.vone.vone.data.dto.PostResponseDto;
import com.vone.vone.data.dao.PostDAO;
import com.vone.vone.data.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostDAO postDAO;
    private final PostDto postDto;

    @Transactional
    public PostResponseDto getPost(Long id)
    {
        Post post = postDAO.selectPost(id);

        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setId(post.getId());
        postResponseDto.setVerifierId(post.getVerifierId());
        postResponseDto.setExpired(post.getExpired());
        postResponseDto.setRequired(post.getRequired());
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setUrl(post.getUrl());
        return postResponseDto;
    }

    @Transactional
    public PostResponseDto savePost(PostDto postDto)
    {
        Post post = new Post();
        post.setVerifierId(postDto.getVerifierId());
        post.setExpired(postDto.getExpired());
        post.setRequired(postDto.getRequired());
        post.setUrl(postDto.getUrl());
        post.setTitle(postDto.getTitle());
        post.setUpdatedAt(LocalDateTime.now());
        post.setCreatedAt(LocalDateTime.now());

        Post savedPost = postDAO.insertPost(post);

        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.setId(savedPost.getId());
        postResponseDto.setExpired(savedPost.getExpired());
        postResponseDto.setTitle(savedPost.getTitle());
        postResponseDto.setUrl(savedPost.getUrl());
        postResponseDto.setRequired(savedPost.getRequired());
        postResponseDto.setVerifierId(savedPost.getVerifierId());

        return postResponseDto;
    }

    @Transactional
    public List<PostResponseDto> getAllPostByVerifier(String verifierId)
    {
        List<Post> posts = postDAO.selectAllPostByVerifier(verifierId);
        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for (Post post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto();
            postResponseDto.setId(post.getId());
            postResponseDto.setVerifierId(post.getVerifierId());
            postResponseDto.setExpired(post.getExpired());
            postResponseDto.setRequired(post.getRequired());
            postResponseDto.setTitle(post.getTitle());
            postResponseDto.setUrl(post.getUrl());
            postResponseDtos.add(postResponseDto);

        }

        return postResponseDtos;
    }

    @Transactional
    public List<PostResponseDto> getAllPost()
    {
        List<Post> posts = postDAO.selectAllPost();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for (Post post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto();
            postResponseDto.setId(post.getId());
            postResponseDto.setVerifierId(post.getVerifierId());
            postResponseDto.setExpired(post.getExpired());
            postResponseDto.setRequired(post.getRequired());
            postResponseDto.setTitle(post.getTitle());
            postResponseDto.setUrl(post.getUrl());
            postResponseDtos.add(postResponseDto);
        }

        return postResponseDtos;
    }



    public PostResponseDto changePostTitle(Long id, String title) throws Exception{

        return null;
    }

    public void deletePost(Long id) throws Exception{

    }
}
