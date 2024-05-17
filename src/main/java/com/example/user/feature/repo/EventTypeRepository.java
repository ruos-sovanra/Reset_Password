package com.example.user.feature.repo;

import com.example.user.domain.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, String> {
}
