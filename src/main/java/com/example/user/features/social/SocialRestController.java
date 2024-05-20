package com.example.user.features.social;

import com.example.user.features.social.dto.PostRequest;
import com.example.user.features.social.dto.PostResponse;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/socials")
@RequiredArgsConstructor
public class SocialRestController {
    private final SocialService socialService;

    @GetMapping
    @Operation(summary = "Get all social posts")
    public BaseResponse<List<PostResponse>> getPosts() {
        return BaseResponse.<List<PostResponse>>ok()
                .setPayload(socialService.getPosts());
    }

    @PostMapping
    @Operation(summary = "Create a social post")
    public BaseResponse<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        return BaseResponse.<PostResponse>createSuccess()
                .setPayload(socialService.createPost(postRequest));
    }

    @PutMapping("/{postId}")
    @Operation(summary = "Update a social post")
    public BaseResponse<PostResponse> updatePost(@PathVariable String postId, @RequestBody PostRequest postRequest) {
        return BaseResponse.<PostResponse>updateSuccess()
                .setPayload(socialService.updatePost(postId, postRequest));
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "Delete a social post")
    public BaseResponse<Void> deletePost(@PathVariable String postId) {
        socialService.deletePost(postId);
        return BaseResponse.<Void>deleteSuccess();
    }
}
