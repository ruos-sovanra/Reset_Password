package com.example.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String password;
    private String confirm_password;
    private String avatar;
    private boolean isDisabled;
    private boolean isVerify;
    private boolean isAccountExpired;
    private boolean isAccountLocked;
    private boolean isCredentialsExpired;
    private LocalDate created_at;
    private Timestamp updated_at;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;


}
