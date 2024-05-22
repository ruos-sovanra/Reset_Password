package com.example.user.mapper;

import com.example.user.domain.ShareComment;
import com.example.user.feature.share_comment.dto.ShareCommentRequest;
import com.example.user.feature.share_comment.dto.ShareCommentResponse;
import com.example.user.feature.share_comment.dto.ShareRepliedRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ShareCommentMapper {

    public abstract ShareCommentResponse toShareCommentResponse(ShareComment shareComment);

    public abstract ShareComment fromRequestToResponse(ShareCommentRequest shareCommentRequest);

    @AfterMapping
    protected void handleNestedComments(ShareComment shareComment, @MappingTarget ShareCommentResponse response) {
        if (shareComment.getParentComment() != null ) {
            ShareCommentResponse nestedResponse = toNestedCommentResponse(shareComment.getParentComment());
            List<ShareCommentResponse> replies = shareComment.getReplies().stream()
                    .map(this::toShareCommentResponse)
                    .collect(Collectors.toList());
            ShareCommentResponse newResponse = ShareCommentResponse.withNestedComment(shareComment, nestedResponse, replies);
            response = newResponse;
        }
    }

    @Named("toNestedCommentResponse")
    protected ShareCommentResponse toNestedCommentResponse(ShareComment shareComment) {
        return ShareCommentResponse.withNestedComment(
                shareComment,
                null,
                null
        );
    }

    public ShareComment responseToComment(ShareRepliedRequest shareCommentRequest, ShareComment parentComment) {
        ShareComment shareComment = new ShareComment();
        shareComment.setComment(shareCommentRequest.comment());
        shareComment.setParentComment(parentComment);
        return shareComment;
    }

    public abstract List<ShareCommentResponse> commentListToCommentResponseList(List<ShareComment> shareComments);
}
