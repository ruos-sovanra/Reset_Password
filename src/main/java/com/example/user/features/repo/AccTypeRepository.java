package com.example.user.features.repo;

import com.example.user.domain.AccType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccTypeRepository extends JpaRepository<AccType, String> {
    Optional<AccType> findByName(String name);
}
