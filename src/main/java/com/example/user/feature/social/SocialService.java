package com.example.user.feature.social;

import com.example.user.feature.social.dto.PostRequest;
import com.example.user.feature.social.dto.PostResponse;
import com.example.user.utils.CustomPage;

import java.util.List;

public interface SocialService {
    PostResponse createPost(PostRequest postRequest);
    CustomPage<PostResponse> getPosts(int page, int size,String baseUrl);
    PostResponse updatePost(String postId, PostRequest postRequest);
    void deletePost(String postId);
}
