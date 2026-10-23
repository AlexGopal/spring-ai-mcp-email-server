package com.example.spring_ai_mcp_email_server.Server;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import com.example.spring_ai_mcp_email_server.Service.EmailService;

@Service
public class EmailMcpTools {

    private final EmailService emailService;

    public EmailMcpTools(EmailService emailService) {
        this.emailService = emailService;
    }

    @McpTool(description = "Send an email")
    public String sendEmail(
            @McpToolParam(description = "Recipient email") String to,
            @McpToolParam(description = "Email subject") String subject,
            @McpToolParam(description = "Email body") String body
    )
    {
        emailService.send(to, subject, body);
        return "Email sent successfully to " + to;
    }
    
}
