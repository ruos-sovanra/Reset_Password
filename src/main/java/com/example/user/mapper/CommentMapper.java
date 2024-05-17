package com.example.user.mapper;

import com.example.user.domain.Comment;
import com.example.user.feature.comment.dto.CommentRequest;
import com.example.user.feature.comment.dto.CommentResponse;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "replies", source = "replies") // Add this line
    @Named("toCommentResponse")
    public abstract CommentResponse toCommentResponse(Comment comment);

    @AfterMapping
    protected void handleNestedComments(Comment comment, @MappingTarget CommentResponse response) {
        if (comment.getParentComment() != null ) {
            // Convert nested comments to CommentResponse objects
            CommentResponse nestedResponse = toNestedCommentResponse(comment.getParentComment());
            // Convert replies to CommentResponse objects
            List<CommentResponse> replies = comment.getReplies().stream()
                    .map(this::toCommentResponse)
                    .collect(Collectors.toList());
            // Create a new CommentResponse with the nested comment and replies
            CommentResponse newResponse = CommentResponse.withNestedComment(comment, nestedResponse, replies);
            // Replace the original response with the new one
            response = newResponse;
        }
    }

    @Named("toNestedCommentResponse")
    protected CommentResponse toNestedCommentResponse(Comment comment) {
        // Create a new CommentResponse object without converting nested comments
        return CommentResponse.withNestedComment(
                comment,
                null, // Nested comment. Adjust this as needed.
                null  // Replies. Adjust this as needed.
        );
    }

    public Comment responseToComment(CommentRequest commentRequest, Comment parentComment) {
        Comment comment = new Comment();
        comment.setComment(commentRequest.comment()); // Set the comment field
        comment.setParentComment(parentComment);
        return comment;
    }
    public abstract List<CommentResponse> commentListToCommentResponseList(List<Comment> comments);
}