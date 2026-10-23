package com.example.spring_ai_mcp_email_server.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

class EmailServiceTest {

    @Test
    void sendUsesConfiguredFromAddress() {
        JavaMailSender mailSender = mock(JavaMailSender.class);
        EmailService service = new EmailService(mailSender, "demo@example.com");

        service.send("recipient@example.com", "Test subject", "Test body");

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());
        SimpleMailMessage sent = captor.getValue();

        assertEquals("demo@example.com", sent.getFrom());
        assertEquals("recipient@example.com", sent.getTo()[0]);
        assertEquals("Test subject", sent.getSubject());
        assertEquals("Test body", sent.getText());
    }
}
