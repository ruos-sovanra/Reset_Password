package com.example.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "al_user_details")
@Getter
@Setter
@NoArgsConstructor
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String nationality;
    private String address;
    private String telephone;

    @Column(name ="educations")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,Object> educations;

    @Column(name ="work_experiences")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,Object> workExperiences;

    @Column(name ="interests")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,Object> interests;

    @Column(name ="achievements")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,Object> achievements;

    @Column(name ="skills")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,Object> skills;

    @Column(name ="languages")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,Object> languages;

    @ManyToOne
    @JoinColumn(name = "generation_id")
    private Generation generation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
