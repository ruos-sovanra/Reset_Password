package com.example.user.domain;

import com.example.user.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "al_forgot_password")
@Getter
@Setter
@NoArgsConstructor
public class ForgotPassword extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer otp;
    private Date expiryDate;
    @OneToOne
    private User user;
}
