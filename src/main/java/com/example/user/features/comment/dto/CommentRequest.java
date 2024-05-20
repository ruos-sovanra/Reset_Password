package com.example.user.features.comment.dto;

import lombok.Builder;

@Builder
public record CommentRequest(
        String userId,
        String socialId,
        String comment,
        String parentCommentId
){
}
