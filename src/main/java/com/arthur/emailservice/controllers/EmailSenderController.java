package com.arthur.emailservice.controllers;

import com.arthur.emailservice.application.EmailSenderUseCaseImpl;
import com.arthur.emailservice.core.EmailRequestDTO;
import com.arthur.emailservice.core.exceptions.EmailSenderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private final EmailSenderUseCaseImpl emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderUseCaseImpl emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequest) {
        try {
            emailSenderService.sendEmail(emailRequest.toEmail(), emailRequest.subject(), emailRequest.body());
            return ResponseEntity.ok("Email sent successfully.");
        } catch(EmailSenderException emailSenderException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email sending failed.");
        }
    }
}
