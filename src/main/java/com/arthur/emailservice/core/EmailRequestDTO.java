package com.arthur.emailservice.core;

public record EmailRequestDTO(String toEmail, String subject, String body) {
}
