package com.example.user.feature.social;

import com.example.user.feature.social.dto.PostRequest;
import com.example.user.feature.social.dto.PostResponse;

import java.util.List;

public interface SocialService {
    PostResponse createPost(PostRequest postRequest);
    List<PostResponse> getPosts();
    PostResponse updatePost(String postId, PostRequest postRequest);
    void deletePost(String postId);
}
