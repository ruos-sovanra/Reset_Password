package com.example.user.domain;

import com.example.user.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "al_thumbnails")
@Getter
@Setter
@NoArgsConstructor
public class Thumbnail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String thumbnailUrl;

    @ManyToOne
    @JoinColumn(name = "social_id")
    private Social social;
}
