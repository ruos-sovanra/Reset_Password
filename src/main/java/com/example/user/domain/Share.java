package com.example.user.domain;

import com.example.user.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "al_shares")
@Setter
@Getter
@NoArgsConstructor
public class Share extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "social_id")
    private Social social;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "share")
    private List<ShareComment> comments;

    private String caption;
    private Integer likes;
    private Integer shares;
}
