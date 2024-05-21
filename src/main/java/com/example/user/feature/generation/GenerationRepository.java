package com.example.user.feature.generation;

import com.example.user.domain.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, String> {
}
