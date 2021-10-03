package com.example.emailMicroService.services;

import com.example.emailMicroService.emailStrategy.EmailSender;
import com.example.emailMicroService.model.EmailStatus;
import com.example.emailMicroService.model.EmailModel;
import com.example.emailMicroService.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    EmailSender emailsender;

    public EmailModel sendEmail(EmailModel emailModel) {
        // Amazon SES (Simple Email Service) or similar for use in production
        //SMTP for demonstration

        boolean result = emailsender.sendEmail(
                emailModel.getEmailFrom(),
                emailModel.getEmailTo(),
                emailModel.getSubject(),
                emailModel.getText());
        if(result){
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setStatusEmail(EmailStatus.SENT);
        }else{
            emailModel.setStatusEmail(EmailStatus.ERROR);
        }
        return emailRepository.save(emailModel);

    }
}
