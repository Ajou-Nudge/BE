package com.vone.vone.service;

import com.vone.vone.data.dto.PostResponseDto;
import com.vone.vone.data.entity.Post;
import com.vone.vone.data.entity.PostDto;

import java.util.List;

public interface PostService {
    PostResponseDto getPost(Long number);

    PostResponseDto savePost(PostDto postDto);

    List<PostResponseDto> getAllPost(String verifierId);

    PostResponseDto changePostTitle(Long number, String title) throws Exception;

    void deletePost(Long number) throws Exception;
}
