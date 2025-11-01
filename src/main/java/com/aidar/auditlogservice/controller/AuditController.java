package com.aidar.auditlogservice.controller;

import com.aidar.auditlogservice.entity.AuditEvent;
import com.aidar.auditlogservice.repository.AuditEventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private final AuditEventRepository auditEventRepository;

    public AuditController(AuditEventRepository auditEventRepository) {
        this.auditEventRepository = auditEventRepository;
    }

    @PostMapping
    public ResponseEntity<?> logEvent(@RequestBody AuditEventRequest request) {
        // Идемпотентность: проверяем, не было ли уже такого eventId
        if (auditEventRepository.findByEventId(request.getEventId()).isPresent()) {
            return ResponseEntity.ok().build(); // уже обработано
        }

        AuditEvent event = new AuditEvent(
                request.getEventId(),
                request.getEventType(),
                request.getUserId(),
                request.getEntityId(),
                request.getDetails()
        );

        auditEventRepository.save(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getEvents(
            @RequestParam String userId,
            @RequestParam String eventType) {
        var events = auditEventRepository.findByUserIdAndEventType(userId, eventType);
        return ResponseEntity.ok(events);
    }

    // Вложенный класс для приёма JSON
    public static class AuditEventRequest {
        private String eventId;
        private String eventType;
        private String userId;
        private String entityId;
        private String details;

        // Геттеры
        public String getEventId() { return eventId; }
        public String getEventType() { return eventType; }
        public String getUserId() { return userId; }
        public String getEntityId() { return entityId; }
        public String getDetails() { return details; }
    }
}