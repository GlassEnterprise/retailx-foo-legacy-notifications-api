package com.retailx.notifications.service;

import com.retailx.notifications.model.NotificationRequest;
import com.retailx.notifications.model.NotificationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Legacy Notification Service
 * 
 * Handles the business logic for sending and tracking notifications.
 * 
 * LEGACY NOTICE:
 * This service uses in-memory storage and mock responses for demonstration.
 * Production implementation should use proper database and external services.
 * 
 * TODOs:
 * - [ ] Replace in-memory storage with database (RETAILX-8888)
 * - [ ] Implement actual email/SMS sending (RETAILX-9999)
 * - [ ] Add rate limiting per recipient (RETAILX-9988)
 * - [ ] Add retry mechanism for failed notifications (RETAILX-1010)
 */
@Service
public class NotificationService {
    
    // TODO: Replace with proper database storage (RETAILX-8888)
    private final Map<String, NotificationResponse> notificationStore = new HashMap<>();
    
    /**
     * Sends a notification (mock implementation)
     * 
     * LEGACY: This is a mock implementation that doesn't actually send notifications.
     * Real implementation should integrate with email/SMS providers.
     */
    public NotificationResponse sendNotification(NotificationRequest request) {
        // Generate unique ID (UUID v4 as per OpenAPI spec)
        String notificationId = UUID.randomUUID().toString();
        
        // TODO: Implement actual notification sending logic (RETAILX-9999)
        // TODO: Add validation for recipient format (RETAILX-3344)
        // TODO: Implement rate limiting check (RETAILX-9988)
        
        // Mock response - in real implementation, status would depend on actual sending result
        NotificationResponse.NotificationStatus status = mockSendingResult(request);
        
        NotificationResponse response = new NotificationResponse(
            notificationId,
            request.getRecipient(),
            request.getMessage(),
            request.getType(),
            status,
            LocalDateTime.now()
        );
        
        // Store in memory (TODO: use proper database)
        notificationStore.put(notificationId, response);
        
        return response;
    }
    
    /**
     * Retrieves notification by ID
     */
    public NotificationResponse getNotificationById(String notificationId) {
        // TODO: Query from database instead of in-memory map (RETAILX-8888)
        return notificationStore.get(notificationId);
    }
    
    /**
     * Mock sending result for demonstration
     * 
     * LEGACY: This simulates notification sending results.
     * Real implementation would depend on actual service responses.
     */
    private NotificationResponse.NotificationStatus mockSendingResult(NotificationRequest request) {
        // Simple mock logic - push notifications fail more often (experimental feature)
        if (request.getType() == NotificationRequest.NotificationType.push) {
            // TODO: Improve push notification reliability (RETAILX-4455)
            return Math.random() > 0.7 ? NotificationResponse.NotificationStatus.sent 
                                       : NotificationResponse.NotificationStatus.failed;
        }
        
        // Email and SMS have better success rates in this mock
        return Math.random() > 0.1 ? NotificationResponse.NotificationStatus.sent 
                                   : NotificationResponse.NotificationStatus.failed;
    }
}
