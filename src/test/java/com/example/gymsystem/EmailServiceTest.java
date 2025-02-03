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
        // Create a spy of the EmailService to intercept calls
        emailService = spy(new EmailService());
    }

    @Test
    void testEnqueueEmail() throws InterruptedException {
        // Define test data
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "This is a test email body.";

        // Enqueue the email
        emailService.enqueueEmail(to, subject, body);

        // Retrieve the email queue from the service
        BlockingQueue<EmailService.EmailTask> emailQueue = emailService.emailQueue;

        // Verify that the email was added to the queue
        assertTrue(emailQueue.size() > 0, "Email should be added to the queue");

        // Retrieve the email task from the queue
        EmailService.EmailTask task = emailQueue.poll(5, TimeUnit.SECONDS); // Wait up to 5 seconds
        assertNotNull(task, "Email task should not be null");

        // Verify the email task details
        assertEquals(to, task.getTo(), "Recipient email should match");
        assertEquals(subject, task.getSubject(), "Subject should match");
        assertEquals(body, task.getBody(), "Body should match");
    }

    @Test
    void testProcessEmailQueue() throws InterruptedException {
        // Define test data
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "This is a test email body.";

        // Enqueue the email
        emailService.enqueueEmail(to, subject, body);

        // Start processing the email queue
        emailService.processEmailQueue();

        // Wait for the email to be processed
        Thread.sleep(3000); // Adjust the sleep time if needed

        // Stop the processing loop
        emailService.stopProcessing();

        // Verify that the email was sent
        verify(emailService, atLeastOnce()).sendEmail(to, subject, body);
    }
}