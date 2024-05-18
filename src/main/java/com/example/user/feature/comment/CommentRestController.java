package com.example.user.feature.comment;

import com.example.user.feature.comment.dto.CommentRequest;
import com.example.user.feature.comment.dto.CommentResponse;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentRestController {

    private final CommentService commentService;

    @GetMapping
    @Operation(summary = "Get all comments")
    public BaseResponse<List<CommentResponse>> getComments() {
        return BaseResponse.<List<CommentResponse>>ok()
                .setPayload(commentService.getComments());
    }

    @GetMapping("/social/{socialId}")
    @Operation(summary = "Get comments by social id")
    public BaseResponse<List<CommentResponse>> getCommentsBySocialId(@PathVariable  String socialId) {
        return BaseResponse.<List<CommentResponse>>ok()
                .setPayload(commentService.getCommentsBySocialId(socialId));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get comments by user id")
    public BaseResponse<List<CommentResponse>> getCommentsByUserId(@PathVariable  String userId) {
        return BaseResponse.<List<CommentResponse>>ok()
                .setPayload(commentService.getCommentsByUserId(userId));
    }

    @GetMapping("/parent/{parentCommentId}")
    @Operation(summary = "Get comments by parent comment id")
    public BaseResponse<List<CommentResponse>> getCommentsByParentComment(@PathVariable  String parentCommentId) {
        return BaseResponse.<List<CommentResponse>>ok()
                .setPayload(commentService.getCommentsByParentComment(parentCommentId));
    }

    @GetMapping("/{commentId}")
    @Operation(summary = "Get comment by id")
    public BaseResponse<CommentResponse> getComment(@PathVariable String commentId) {
        return BaseResponse.<CommentResponse>ok()
                .setPayload(commentService.getComment(commentId));
    }


    @PostMapping
    @Operation(summary = "Create comment")
    public BaseResponse<CommentResponse> createComment(@RequestBody CommentRequest commentRequest) {
        return BaseResponse.<CommentResponse>updateSuccess()
                .setPayload(commentService.createComment(commentRequest));
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "Update comment")
    public BaseResponse<CommentResponse> updateComment(@PathVariable String commentId, @RequestBody CommentRequest commentRequest) {
        return BaseResponse.<CommentResponse>updateSuccess()
                .setPayload(commentService.updateComment(commentId, commentRequest));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Delete comment")
    public BaseResponse<Void> deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
        return BaseResponse.<Void>deleteSuccess();
    }


}
