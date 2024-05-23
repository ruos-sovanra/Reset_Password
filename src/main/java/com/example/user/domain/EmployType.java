package com.example.user.domain;

import com.example.user.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "al_employ_types")
@Setter
@Getter
@NoArgsConstructor
public class EmployType extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String employType;
}
