package com.example.user.feature.comment.dto;

import lombok.Builder;

@Builder
public record RepliedRequest(
        String userId,
        String socialId,
        String comment,
        String parentCommentId
) {
}
