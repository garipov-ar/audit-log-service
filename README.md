# Audit Log Service

> Immutable audit logging service for financial operations.  
> Ensures compliance, idempotency, and traceability.

![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5+-6DB33F?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?logo=postgresql&logoColor=white)

## ✨ Features

- **Immutable logs** — records cannot be modified or deleted
- **Idempotency** — duplicate `eventId` are ignored
- **Fast search** by user and event type
- **Docker-ready** — one-command setup

## 🚀 Quick Start

```bash
docker-compose up -d
./mvnw spring-boot:run -Dspring-boot.run.profiles=docker
```

Log an event:
```bash
curl -X POST http://localhost:8080/audit -H "Content-Type: application/json" -d '{
  "eventId": "evt-001",
  "eventType": "PAYMENT_INITIATED",
  "userId": "user-123",
  "entityId": "payment-456",
  "details": "{\"amount\":10000}"
}'
```