package com.example.user.feature.mail;

import com.example.user.feature.mail.dto.MailBody;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void sendSimpleMessage(MailBody mailBody) {
        // send mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBody.to());
        message.setFrom("vannraruos@gmail.com");
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());

        mailSender.send(message);

    }

}
