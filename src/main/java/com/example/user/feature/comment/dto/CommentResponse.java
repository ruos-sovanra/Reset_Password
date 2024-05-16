package com.example.user.feature.comment.dto;

import com.example.user.domain.Comment;
import lombok.Builder;


@Builder
public record CommentResponse(
        String id,
        String userName,
        String comment,
        CommentResponse nestedComment

) {
    public static CommentResponse withNestedComment(Comment comment, CommentResponse nestedComment) {
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getUsername(),
                comment.getComment(),
                nestedComment
        );
    }
}