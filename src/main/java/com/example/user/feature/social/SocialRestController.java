package com.example.user.feature.social;

import com.example.user.feature.social.dto.LikeUpdateRequest;
import com.example.user.feature.social.dto.PostRequest;
import com.example.user.feature.social.dto.PostResponse;
import com.example.user.utils.BaseResponse;
import com.example.user.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/socials")
@RequiredArgsConstructor
public class SocialRestController {
    private final SocialService socialService;

    @GetMapping
    @Operation(summary = "Get all social posts")
    public ResponseEntity<CustomPage<PostResponse>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/api/v1/socials";
        CustomPage<PostResponse> postResponseCustomPage = socialService.getPosts(page, size, baseUrl);
        return ResponseEntity.ok(postResponseCustomPage);
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

    @PatchMapping("/{postId}/likes")
    @Operation(summary = "Update likes of a social post")
    public BaseResponse<PostResponse> updateLikes(@PathVariable String postId, @RequestBody LikeUpdateRequest likeUpdateRequest) {
        return BaseResponse.<PostResponse>updateSuccess()
                .setPayload(socialService.updateLikes(postId, likeUpdateRequest));
    }

}
