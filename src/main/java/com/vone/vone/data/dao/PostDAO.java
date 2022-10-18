package com.vone.vone.data.dao;

import com.vone.vone.data.entity.Post;

import java.util.ArrayList;
import java.util.List;

public interface PostDAO {
    Post insertPost(Post post);

    Post selectPost(Long number);

    List<Post> selectAllPost();

    Post updatePostnTitle(Long number, String title) throws Exception;

    void deletePost(Long number) throws Exception;
}
