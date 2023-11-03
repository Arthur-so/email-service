package com.arthur.emailservice.application;

import com.arthur.emailservice.adapters.EmailSenderGateway;
import com.arthur.emailservice.core.cases.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderUseCaseImpl implements EmailSenderUseCase {

    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderUseCaseImpl(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        this.emailSenderGateway.sendEmail(toEmail, subject, body);
    }
}
