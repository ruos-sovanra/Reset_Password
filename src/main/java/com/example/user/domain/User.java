package com.example.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "al_users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
    private String avatar;
    private Boolean isDisabled;
    private Boolean isVerified;
    private Boolean isAdmin;
    private Boolean isAccountExpired;
    private Boolean isAccountLocked;
    private Boolean isCredentialsExpired;
    private String coverUrl;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @ManyToOne
    @JoinColumn(name = "acc_type_id")
    private AccType accType;

    @OneToOne(mappedBy = "user")
    private UserDetail userDetail;



}
