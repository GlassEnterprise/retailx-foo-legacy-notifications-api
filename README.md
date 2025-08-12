# Legacy Notification Service API

## Overview

The **foo-legacy-notifications-api** is a legacy notification service for the RetailX platform, responsible for delivering order updates, promotions, and alerts to customers and staff. This service provides a REST API for sending and tracking notifications via email, SMS, and experimental push notifications.

> **⚠️ LEGACY NOTICE**  
> This API is part of the legacy notification infrastructure and is scheduled for migration to the new **RetailX Messaging Hub**. Please refer to the TODOs section for migration plans and current limitations.

## Version

**Current Version:** 1.2.7

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Maven 3.9.5** (via wrapper)
- **Docker** support included

## API Endpoints

### Base URL
```
http://localhost:8081/v1
```

### Implemented Endpoints

#### 1. Send Notification
```http
POST /v1/notifications
Content-Type: application/json

{
  "recipient": "user@example.com",
  "message": "Your order has been shipped!",
  "type": "email"
}
```

**Response (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "recipient": "user@example.com",
  "message": "Your order has been shipped!",
  "type": "email",
  "status": "sent",
  "createdAt": "2024-01-15T10:30:00"
}
```

#### 2. Get Notification Status
```http
GET /v1/notifications/{notificationId}
```

**Response (200 OK):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "recipient": "user@example.com",
  "message": "Your order has been shipped!",
  "type": "email",
  "status": "sent",
  "createdAt": "2024-01-15T10:30:00"
}
```

### Supported Notification Types
- `email` - ✅ Fully supported
- `sms` - ✅ Fully supported  
- `push` - ⚠️ Experimental (higher failure rate)

### Status Values
- `pending` - Notification queued for delivery
- `sent` - Notification successfully sent
- `failed` - Notification delivery failed

## Running Locally

### Prerequisites
- Java 17 or higher
- No Maven installation required (uses wrapper)

### Quick Start

1. **Clone and navigate to the project:**
   ```bash
   cd foo-legacy-notifications-api
   ```

2. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **The service will start on port 8081:**
   ```
   http://localhost:8081
   ```

4. **Test the API:**
   ```bash
   # Send a notification
   curl -X POST http://localhost:8081/v1/notifications \
     -H "Content-Type: application/json" \
     -d '{
       "recipient": "test@example.com",
       "message": "Hello from RetailX!",
       "type": "email"
     }'

   # Check notification status (replace {id} with actual ID from response)
   curl http://localhost:8081/v1/notifications/{id}
   ```

### Building

```bash
# Build the project
./mvnw clean package

# Run tests
./mvnw test

# Build Docker image
docker build -t foo-legacy-notifications-api .

# Run with Docker
docker run -p 8081:8081 foo-legacy-notifications-api
```

## Dependencies

### Runtime Dependencies
- `spring-boot-starter-web` - REST API framework
- `spring-boot-starter-validation` - Request validation

### Development Dependencies
- `spring-boot-starter-test` - Testing framework

## Project Structure

```
foo-legacy-notifications-api/
├── src/main/java/com/retailx/notifications/
│   ├── NotificationServiceApplication.java    # Main application class
│   ├── controller/
│   │   └── NotificationController.java        # REST endpoints
│   ├── model/
│   │   ├── NotificationRequest.java           # Request model
│   │   └── NotificationResponse.java          # Response model
│   └── service/
│       └── NotificationService.java           # Business logic
├── src/main/resources/
│   └── application.properties                 # Configuration
├── Dockerfile                                 # Container configuration
├── pom.xml                                    # Maven configuration
└── README.md                                  # This file
```

## Legacy Limitations

### Current Limitations
- **Plain Text Only**: No support for HTML or rich content messages
- **In-Memory Storage**: No persistent database (data lost on restart)
- **No Rate Limiting**: Rate limiting per recipient not implemented
- **Limited Status Tracking**: No delivery confirmation or retry information
- **No Pagination**: List endpoints don't support pagination
- **Experimental Push**: Push notifications have higher failure rates

### Mock Implementation
This service uses mock responses for demonstration purposes:
- Notifications are not actually sent to external services
- Status is randomly generated based on notification type
- Data is stored in memory only

## TODOs & Migration Plans

### High Priority (Migration to RetailX Messaging Hub)
- [ ] **RETAILX-1234**: Migrate notification types to support in-app messaging
- [ ] **RETAILX-5678**: Add support for multi-language templates
- [ ] **RETAILX-9101**: Deprecate SMS notifications in favor of push
- [ ] **RETAILX-1121**: Audit notification delivery tracking

### Medium Priority (Infrastructure)
- [ ] **RETAILX-8888**: Replace in-memory storage with database
- [ ] **RETAILX-9999**: Implement actual email/SMS sending
- [ ] **RETAILX-9988**: Add rate limiting per recipient
- [ ] **RETAILX-2233**: Implement pagination for list endpoints

### Low Priority (Enhancements)
- [ ] **RETAILX-4567**: Add support for scheduled notifications
- [ ] **RETAILX-3344**: Add validation for recipient format
- [ ] **RETAILX-4455**: Fully implement push notifications
- [ ] **RETAILX-5432**: Add proper error handling and status codes

### DevOps & Monitoring
- [ ] **RETAILX-5555**: Add proper health checks and monitoring
- [ ] **RETAILX-6666**: Implement graceful shutdown handling
- [ ] **RETAILX-7777**: Add startup banner with migration notice
- [ ] **RETAILX-1111**: Use multi-stage Docker build
- [ ] **RETAILX-2222**: Add non-root user for Docker security

## Contact

For questions or migration planning, contact the **RetailX Platform Team**.

## License

Internal RetailX Platform Service - Not for external distribution.

---

**⚠️ Migration Notice**: This service is scheduled for deprecation. New integrations should use the RetailX Messaging Hub API instead.
