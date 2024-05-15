package com.example.user.feature.mail;

import com.example.user.domain.ForgotPassword;
import com.example.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, String>{
    @Query("select  fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
    ForgotPassword findByOtpAndUser(Integer otp, User user);

    ForgotPassword findByUser(User user);
}
