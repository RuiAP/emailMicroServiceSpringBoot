package com.example.emailMicroService.emailStrategy;

public interface EmailSender {

     boolean sendEmail(String emailFrom, String emailTo, String subject,  String emailContent);
}
