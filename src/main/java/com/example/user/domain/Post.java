package com.example.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "al_posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String description;
    private String thumbnail;
    private Integer likes;
    private Integer share;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "post_type_id")
    private PostType postType;



}
