package com.example.user.features.comment;

import com.example.user.features.comment.dto.CommentRequest;
import com.example.user.features.comment.dto.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CommentRequest commentRequest);
    CommentResponse getComment(String commentId);
    CommentResponse updateComment(String commentId, CommentRequest commentRequest);
    void deleteComment(String commentId);
    List<CommentResponse> getCommentsBySocialId(String socialId);
    List<CommentResponse> getCommentsByUserId(String userId);
    List<CommentResponse> getCommentsByParentComment(String parentCommentId);
    List<CommentResponse> getComments();
}
