package com.example.user.feature.comment;

import com.example.user.feature.comment.dto.CommentRequest;
import com.example.user.feature.comment.dto.CommentResponse;
import com.example.user.feature.comment.dto.RepliedRequest;
import com.example.user.utils.CustomPage;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CommentRequest commentRequest);
    CommentResponse createRepliedComment(RepliedRequest repliedRequest);
    CommentResponse getComment(String commentId);
    CommentResponse updateComment(String commentId, CommentRequest commentRequest);
    void deleteComment(String commentId);
    List<CommentResponse> getCommentsBySocialId(String socialId);
    List<CommentResponse> getCommentsByUserId(String userId);
    List<CommentResponse> getCommentsByParentComment(String parentCommentId);
    CustomPage<CommentResponse> getComments(int page, int size, String baseUrl);
}
