package com.example.user.feature.share_comment.dto;

import com.example.user.domain.ShareComment;
import lombok.Builder;

import java.util.List;

@Builder
public record ShareCommentResponse(
        String id,
        String userName,
        String comment,
        List<ShareCommentResponse> replied
) {
    public static ShareCommentResponse withNestedComment(ShareComment comment, ShareCommentResponse nestedComment, List<ShareCommentResponse> replies) {
        return new ShareCommentResponse(
                comment.getId(),
                comment.getUser().getUsername(),
                comment.getComment(),
                replies
        );
    }
}
