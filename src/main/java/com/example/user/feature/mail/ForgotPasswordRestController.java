package com.example.user.feature.mail;

import com.example.user.domain.ForgotPassword;
import com.example.user.domain.User;
import com.example.user.feature.mail.dto.ChangePassword;
import com.example.user.feature.mail.dto.MailBody;
import com.example.user.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forgot-password")
public class ForgotPasswordRestController {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("verify-email/{email}")
    public ResponseEntity<?> forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        int otp = generateOTP();
        MailBody mailBody = MailBody.builder()
                .to(user.getEmail())
                .subject("Reset Password")
                .text("Here is the OTP to reset your password: "+otp)
                .build();
        ForgotPassword fp = new ForgotPassword();
        fp.setOtp(otp);
        fp.setUser(user);
        fp.setExpiryDate(new Date(System.currentTimeMillis() + 70 * 1000));

        mailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);
        return ResponseEntity.ok("OTP sent to your email");
    }

    @PostMapping("verify-otp/{otp}/{email}")
    public ResponseEntity<String> verifyOTP(@PathVariable String email,@PathVariable Integer otp) {
        User user = userRepository.findByEmail(email);
        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp,user).orElseThrow(() -> new NoSuchElementException("Invalid OTP"));

        if (fp.getExpiryDate().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getId());
            return new ResponseEntity<>("OTP expired", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("OTP verified", HttpStatus.OK);
    }

    @PostMapping("reset-password/{email}")
    public ResponseEntity<String> resetPassword(@RequestBody ChangePassword changePassword, String email) {

        if(!Objects.equals(changePassword.password(), changePassword.repeatPassword())){
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        //change to encoded Password later
        String password = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassword(email, password);
        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }

    private Integer generateOTP() {
        Random random = new Random();
        return random.nextInt(100000,999999);
    }
}
