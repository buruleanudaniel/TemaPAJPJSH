package com.example.gymsystem;

import com.example.gymsystem.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    private EmailService emailService;

    @BeforeEach
    void setUp() {
        emailService = spy(new EmailService());
    }

    @Test
    void testEnqueueEmail() throws InterruptedException {
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "This is a test email body.";

        emailService.enqueueEmail(to, subject, body);

        BlockingQueue<EmailService.EmailTask> emailQueue = emailService.emailQueue;

        assertTrue(emailQueue.size() > 0, "Email should be added to the queue");

        EmailService.EmailTask task = emailQueue.poll(5, TimeUnit.SECONDS);
        assertNotNull(task, "Email task should not be null");

        assertEquals(to, task.getTo(), "Recipient email should match");
        assertEquals(subject, task.getSubject(), "Subject should match");
        assertEquals(body, task.getBody(), "Body should match");
    }

    @Test
    void testProcessEmailQueue() throws InterruptedException {
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "This is a test email body.";

        emailService.enqueueEmail(to, subject, body);

        emailService.processEmailQueue();

        Thread.sleep(3000);

        emailService.stopProcessing();

        verify(emailService, atLeastOnce()).sendEmail(to, subject, body);
    }
}