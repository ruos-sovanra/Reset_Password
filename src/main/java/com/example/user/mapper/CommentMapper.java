package com.example.user.mapper;

import com.example.user.domain.Comment;
import com.example.user.feature.comment.dto.CommentRequest;
import com.example.user.feature.comment.dto.CommentResponse;
import com.example.user.feature.comment.dto.RepliedRequest;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "replies", source = "replies")
    @Mapping(target = "parentCommentId", expression = "java(comment.getParentComment() != null ? comment.getParentComment().getId() : null)") // existing line
    @Mapping(target = "replied", expression = "java(comment.getParentComment() != null)") // Add this line

    public abstract CommentResponse toCommentResponse(Comment comment);

    public abstract Comment fromRequestToResponse(CommentRequest commentRequest);

    @AfterMapping
    protected void handleNestedComments(Comment comment, @MappingTarget CommentResponse response) {
        if (comment.getParentComment() != null ) {
            // Convert replies to CommentResponse objects
            List<CommentResponse> replies = comment.getReplies().stream()
                    .map(this::toCommentResponse)
                    .collect(Collectors.toList());
            // Create a new CommentResponse with the nested comment and replies
            CommentResponse newResponse = CommentResponse.withNestedComment(comment, replies);
            // Replace the original response with the new one
            response = newResponse;
        }
    }

    public Comment responseToComment(RepliedRequest commentRequest, Comment parentComment) {
        Comment comment = new Comment(); // Set the comment field
        comment.setParentComment(parentComment);
        return comment;
    }

    public abstract List<CommentResponse> commentListToCommentResponseList(List<Comment> comments);
}