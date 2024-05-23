package com.example.user.domain;

import com.example.user.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "al_socials")
@Setter
@Getter
@NoArgsConstructor
public class Social extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String caption;

    @OneToMany(mappedBy = "social")
    private List<Thumbnail> thumbnails;

    private Integer likes;
    private Integer shares;

    @ManyToOne
    @JoinColumn(name = "post_type_id")
    private PostType postType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "social", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}
