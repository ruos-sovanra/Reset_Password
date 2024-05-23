package com.example.user.domain;

import com.example.user.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "al_accTypes")
@Getter
@Setter
public class AccType extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @OneToMany(mappedBy = "accType")
    private List<User> users;
}
