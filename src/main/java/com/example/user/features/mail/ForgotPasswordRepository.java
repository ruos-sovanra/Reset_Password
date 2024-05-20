package com.example.user.features.mail;

import com.example.user.domain.ForgotPassword;
import com.example.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, String>{
    @Query("select  fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
    ForgotPassword findByOtpAndUser(Integer otp, User user);

    ForgotPassword findByUser(User user);
}
