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
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    private String confirm_password;
    private String avatar;
    private boolean is_disabled;
    private boolean is_verified;
    private boolean isAdmin;
    private boolean isAccountExpired;
    private boolean isAccountLocked;
    private boolean isCredentialsExpired;
    private LocalDate created_at;
    private Timestamp updated_at;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @ManyToOne
    @JoinColumn(name = "acc_type_id")
    private AccType accType;


}
