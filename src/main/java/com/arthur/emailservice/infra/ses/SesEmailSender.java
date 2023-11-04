package com.arthur.emailservice.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.arthur.emailservice.adapters.EmailSenderGateway;
import com.arthur.emailservice.core.exceptions.EmailSenderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private static final String emailSender = "joe@doe.com";

    private final AmazonSimpleEmailService sesClient;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService sesClient) {
        this.sesClient = sesClient;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SendEmailRequest sendEmailRequest = new SendEmailRequest()
                .withSource(emailSender)
                .withDestination(new Destination().withToAddresses(toEmail))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body()
                                .withText(new Content(body)))
                );
        try {
            this.sesClient.sendEmail(sendEmailRequest);
        } catch (AmazonServiceException amazonServiceException) {
            throw new EmailSenderException("Email sending failed", amazonServiceException);
        }
    }
}
