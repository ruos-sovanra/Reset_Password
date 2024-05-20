package com.example.user.features.comment.dto;

import com.example.user.domain.Comment;
import lombok.Builder;

import java.util.List;

@Builder
public record CommentResponse(
        String id,
        String userName,
        String comment,
        List<CommentResponse> replies // Add this line
) {
    public static CommentResponse withNestedComment(Comment comment, CommentResponse nestedComment, List<CommentResponse> replies) {
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getUsername(),
                comment.getComment(),
                replies // Add this line
        );
    }
}