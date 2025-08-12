package com.retailx.notifications.model;

import java.time.LocalDateTime;

/**
 * Notification Response Model
 * 
 * Represents the response after sending or querying a notification.
 * 
 * LEGACY NOTICE:
 * This model only supports basic status tracking. Advanced delivery
 * tracking and retry information will be available in the new messaging hub.
 * 
 * TODOs:
 * - [ ] Add delivery attempt history (RETAILX-1121)
 * - [ ] Add retry count and next retry time (RETAILX-2233)
 * - [ ] Add delivery confirmation timestamp (RETAILX-3456)
 */
public class NotificationResponse {
    
    private String id;
    private String recipient;
    private String message;
    private NotificationRequest.NotificationType type;
    private NotificationStatus status;
    private LocalDateTime createdAt;
    
    public NotificationResponse() {}
    
    public NotificationResponse(String id, String recipient, String message, 
                              NotificationRequest.NotificationType type, 
                              NotificationStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.recipient = recipient;
        this.message = message;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public NotificationRequest.NotificationType getType() {
        return type;
    }
    
    public void setType(NotificationRequest.NotificationType type) {
        this.type = type;
    }
    
    public NotificationStatus getStatus() {
        return status;
    }
    
    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    /**
     * Notification Status Enum
     * 
     * LEGACY: Only basic status values are supported.
     * More granular status tracking is planned for the new messaging hub.
     */
    public enum NotificationStatus {
        pending,   // Notification queued for delivery
        sent,      // Notification successfully sent
        failed     // Notification delivery failed
        // TODO: Add 'delivered', 'read', 'clicked' statuses (RETAILX-1121)
    }
}
