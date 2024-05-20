package com.example.user.features.repo;

import com.example.user.domain.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostTypeRepository extends JpaRepository<PostType, String> {
    Optional<PostType> findByType(String type);
}
