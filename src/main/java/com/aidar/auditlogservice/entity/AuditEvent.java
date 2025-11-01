package com.aidar.auditlogservice.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "audit_events", indexes = {
        @Index(name = "idx_user_event", columnList = "user_id,event_type"),
        @Index(name = "idx_event_id", columnList = "event_id", unique = true)
})
public class AuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "event_id", nullable = false, unique = true)
    private String eventId; // для идемпотентности

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "entity_id")
    private String entityId;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    // Конструкторы
    public AuditEvent() {}

    public AuditEvent(String eventId, String eventType, String userId, String entityId, String details) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.userId = userId;
        this.entityId = entityId;
        this.details = details;
    }

    // Геттеры
    public UUID getId() { return id; }
    public String getEventId() { return eventId; }
    public String getEventType() { return eventType; }
    public String getUserId() { return userId; }
    public String getEntityId() { return entityId; }
    public String getDetails() { return details; }
    public Instant getCreatedAt() { return createdAt; }
}