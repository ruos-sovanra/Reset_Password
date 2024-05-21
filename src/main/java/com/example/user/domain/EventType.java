package com.example.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;

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


    private Time eventStart;
    private Time eventEnd;

    //posttype relatioship

    @ManyToOne
    @JoinColumn(name = "post_type")
    private PostType postType;


    //donations relationship
    @ManyToOne
    @JoinColumn(name = "event_type")
    private Donation donations;


}
