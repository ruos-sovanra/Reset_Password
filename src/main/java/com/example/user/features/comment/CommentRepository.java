package com.example.user.features.comment;

import com.example.user.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findBySocialId(String socialId);
    List<Comment> findByUserId(String userId);
    List<Comment> findByParentCommentId(String parentCommentId);
}
