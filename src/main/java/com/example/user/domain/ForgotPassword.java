package com.example.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "forgot_password")
@Getter
@Setter
@NoArgsConstructor
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer otp;
    private Date expiryDate;
    @OneToOne
    private User user;
}
