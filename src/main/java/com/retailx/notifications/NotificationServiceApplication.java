package com.retailx.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Legacy Notification Service Application
 * 
 * This is the main entry point for the RetailX Legacy Notification Service.
 * 
 * LEGACY NOTICE:
 * This service is part of the legacy notification infrastructure and is scheduled 
 * for migration to the new RetailX Messaging Hub.
 * 
 * TODOs:
 * - [ ] Migrate to new RetailX Messaging Hub architecture (RETAILX-1234)
 * - [ ] Add proper health checks and monitoring (RETAILX-5555)
 * - [ ] Implement graceful shutdown handling (RETAILX-6666)
 * 
 * @author RetailX Platform Team
 * @version 1.2.7
 */
@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        // TODO: Add startup banner with migration notice (RETAILX-7777)
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
