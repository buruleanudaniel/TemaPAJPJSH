package com.example.gymsystem.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class EmailService {

    // Keep the queue package-private (or protected if needed)
    public final BlockingQueue<EmailTask> emailQueue = new LinkedBlockingQueue<>();

    private volatile boolean isRunning = true; // Flag to control the loop

    public void enqueueEmail(String to, String subject, String body) {
        EmailTask task = new EmailTask(to, subject, body);
        emailQueue.add(task); // Add the email task to the queue
        System.out.println("Email added to queue: " + task);
    }

    @Async
    public void processEmailQueue() {
        while (isRunning && !emailQueue.isEmpty()) { // Stop when the flag is false or the queue is empty
            try {
                EmailTask task = emailQueue.poll(5, java.util.concurrent.TimeUnit.SECONDS); // Poll with timeout
                if (task != null) {
                    sendEmail(task.getTo(), task.getSubject(), task.getBody());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // Exit the loop if interrupted
            }
        }
    }

    public void stopProcessing() {
        isRunning = false; // Set the flag to false to stop the loop
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            Thread.sleep(2000); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Email sent to: " + to);
    }

    // Make the inner class public
    public static class EmailTask {
        private final String to;
        private final String subject;
        private final String body;

        public EmailTask(String to, String subject, String body) {
            this.to = to;
            this.subject = subject;
            this.body = body;
        }

        public String getTo() {
            return to;
        }

        public String getSubject() {
            return subject;
        }

        public String getBody() {
            return body;
        }

        @Override
        public String toString() {
            return "EmailTask{" +
                    "to='" + to + '\'' +
                    ", subject='" + subject + '\'' +
                    '}';
        }
    }
}