package com.example.user.feature.share_comment;

import com.example.user.domain.ShareComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareCommentRepository extends JpaRepository<ShareComment, String> {
    Page<ShareComment> findAll(Pageable pageable);
}
