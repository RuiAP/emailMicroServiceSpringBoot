package com.example.emailMicroService.consumers;

import com.example.emailMicroService.dto.EmailDTO;
import com.example.emailMicroService.model.EmailModel;
import com.example.emailMicroService.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);
        logger.info("Received RabbitMQ request: {}", emailModel);
        emailModel = emailService.sendEmail(emailModel);

        if(emailModel.wasEmailSent()){
            logger.info("Email sent successfully.  ID: {}, Status: {}, To: {} ",
                    emailModel.getEmailId(),
                    emailModel.getStatusEmail(),
                    emailModel.getEmailTo());
        }else{
            logger.warn("Email not sent correctly! ID: {}, Status: {}, To: {} ",
                    emailModel.getEmailId(),
                    emailModel.getStatusEmail(),
                    emailModel.getEmailTo());
        }
    }
}
