package com.example.user.features.event;

import com.example.user.domain.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventType,String> {

    Optional<EventType> findEventsById(String id);
}
