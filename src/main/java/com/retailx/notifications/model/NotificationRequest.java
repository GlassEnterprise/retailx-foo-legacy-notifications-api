package com.retailx.notifications.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Notification Request Model
 * 
 * Represents a request to send a notification to a recipient.
 * 
 * LEGACY NOTICE:
 * This model only supports basic notification types and plain text messages.
 * Rich content support is planned for the new messaging hub.
 * 
 * TODOs:
 * - [ ] Add support for rich content/HTML messages (RETAILX-1234)
 * - [ ] Add validation for recipient format (RETAILX-3344)
 * - [ ] Add support for scheduled notifications (RETAILX-4567)
 */
public class NotificationRequest {
    
    @NotBlank(message = "Recipient is required")
    private String recipient;
    
    @NotBlank(message = "Message is required")
    private String message;
    
    private NotificationType type;
    
    public NotificationRequest() {}
    
    public NotificationRequest(String recipient, String message, NotificationType type) {
        this.recipient = recipient;
        this.message = message;
        this.type = type;
    }
    
    // Getters and Setters
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
    
    public NotificationType getType() {
        return type;
    }
    
    public void setType(NotificationType type) {
        this.type = type;
    }
    
    /**
     * Notification Type Enum
     * 
     * LEGACY: Only email and sms are fully supported.
     * Push notifications are experimental (RETAILX-4455).
     */
    public enum NotificationType {
        email,
        sms,
        push  // TODO: Fully implement push notifications (RETAILX-4455)
    }
}
