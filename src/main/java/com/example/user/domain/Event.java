package com.example.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "al_events")
public class Event {
    @Id
    private String id;
}
