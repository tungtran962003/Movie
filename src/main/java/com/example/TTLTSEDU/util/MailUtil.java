package com.example.TTLTSEDU.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

//    @Autowired
//    private JavaMailSender emailSender = new JavaMailSenderImpl();

//    @Value("{$spring.mail.username}")
    private String fromMail = "tungtran962003@gmail.com";

    @Autowired
    private JavaMailSender emailSender;

    public String confirmEmail(String recipientName, String confirmationCode) {
        return "Hi " + recipientName + ", your confirmation code: " + confirmationCode;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }



}
