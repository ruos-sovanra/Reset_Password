package com.example.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "al_events")
@Setter
@Getter
@NoArgsConstructor
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String eventName;
    private String eventPoster;
    @Column(columnDefinition = "TEXT")
    private String eventDescription;
    private LocalDateTime scheduledDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //posttype relatioship

    @ManyToOne
    @JoinColumn(name = "post_type")
    private PostType postType;


    //donations relationship
    @ManyToOne
    @JoinColumn(name = "event_type")
    private Donation donations;


}
