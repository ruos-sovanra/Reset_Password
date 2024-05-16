package com.example.user.feature.comment.dto;

import lombok.Builder;

@Builder
public record CommentRequest(
        String userId,
        String socialId,
        String comment,
        String parentCommentId
){
}
