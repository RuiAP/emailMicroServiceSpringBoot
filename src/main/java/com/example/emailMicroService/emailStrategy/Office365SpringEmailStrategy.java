package com.example.emailMicroService.emailStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Office365SpringEmailStrategy implements EmailSender {

    @Autowired
    private JavaMailSender emailSender;


    @Override
    public boolean sendEmail(String emailFrom, String emailTo, String subject, String emailContent) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailTo);
            message.setSubject(subject);
            message.setText(emailContent);
            emailSender.send(message);
            return true;
        }catch(MailException e){
            return false;
        }
    }
}
