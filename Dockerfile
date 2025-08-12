# Legacy Notification Service Dockerfile
# 
# LEGACY NOTICE:
# This Dockerfile uses basic configuration for backward compatibility.
# Modern containerization practices should be applied during migration.
#
# TODOs:
# - [ ] Use multi-stage build for smaller image size (RETAILX-1111)
# - [ ] Add non-root user for security (RETAILX-2222)
# - [ ] Implement proper health checks (RETAILX-3333)
# - [ ] Add build-time security scanning (RETAILX-4444)

FROM openjdk:17-jdk-slim

# TODO: Create non-root user for security (RETAILX-2222)
WORKDIR /app

# Copy Maven wrapper and pom.xml for dependency caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# TODO: Use multi-stage build to avoid including Maven in final image (RETAILX-1111)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# TODO: Add proper health check endpoint and configure here (RETAILX-3333)
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8081/actuator/health || exit 1

# Expose port 8081 (legacy notification service port)
EXPOSE 8081

# TODO: Run as non-root user (RETAILX-2222)
CMD ["java", "-jar", "target/foo-legacy-notifications-api-1.2.7.jar"]
