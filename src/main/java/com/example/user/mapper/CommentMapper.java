package com.example.user.mapper;

import com.example.user.domain.Comment;
import com.example.user.feature.comment.dto.CommentRequest;
import com.example.user.feature.comment.dto.CommentResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Mapping(target = "userName", source = "user.username")
    @Named("toCommentResponse")
    public abstract CommentResponse toCommentResponse(Comment comment);

    @AfterMapping
    protected void handleNestedComments(Comment comment, @MappingTarget CommentResponse response) {
        if (comment.getParentComment() != null ) {
            // Convert nested comments to CommentResponse objects
            CommentResponse nestedResponse = toNestedCommentResponse(comment.getParentComment());
            // Create a new CommentResponse with the nested comment
            CommentResponse newResponse = CommentResponse.withNestedComment(comment, nestedResponse);
            // Replace the original response with the new one
            response = newResponse;
        }
    }

    @Named("toNestedCommentResponse")
    protected CommentResponse toNestedCommentResponse(Comment comment) {
        // Create a new CommentResponse object without converting nested comments
        return CommentResponse.withNestedComment(
                comment,
                null // Nested comment. Adjust this as needed.
        );
    }

    public Comment responseToComment(CommentRequest commentRequest, Comment parentComment) {
        Comment comment = new Comment();
        comment.setComment(commentRequest.comment()); // Set the comment field
        comment.setParentComment(parentComment);
        return comment;
    }
}