package com.vone.vone.service;

import com.vone.vone.data.dto.PostResponseDto;
import com.vone.vone.data.entity.PostDto;

import java.util.List;

public interface PostService {
    PostResponseDto getPost(Long number);

    PostResponseDto savePost(PostDto postDto);

    List<PostResponseDto> getAllPostByVerifier(String verifierId);

    List<PostResponseDto> getAllPost();



    PostResponseDto changePostTitle(Long number, String title) throws Exception;

    void deletePost(Long number) throws Exception;
}
