package com.example.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "al_post_types")
@Getter
@Setter
@NoArgsConstructor
public class PostType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;

    @OneToMany(mappedBy = "postType")
    private List<Post> posts;
}
