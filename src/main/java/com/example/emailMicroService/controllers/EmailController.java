package com.example.emailMicroService.controllers;

import com.example.emailMicroService.dto.EmailDTO;
import com.example.emailMicroService.model.EmailModel;
import com.example.emailMicroService.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/emails")
public class EmailController {


    private final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;


    @PostMapping()
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDTO){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);

        logger.info("Received POST request: {}", emailModel);
        emailModel = emailService.sendEmail(emailModel);

        if(emailModel.wasEmailSent()){
            logger.info("Email sent successfully. ID: {}, Status: {}, To: {} ",
                    emailModel.getEmailId(),
                    emailModel.getStatusEmail(),
                    emailModel.getEmailTo());
        }else{
            logger.warn("Email not sent correctly! ID: {}, Status: {}, To: {} ",
                    emailModel.getEmailId(),
                    emailModel.getStatusEmail(),
                    emailModel.getEmailTo());
        }
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);

    }
}
