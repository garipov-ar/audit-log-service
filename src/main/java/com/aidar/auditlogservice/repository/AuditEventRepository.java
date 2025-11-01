package com.aidar.auditlogservice.repository;


import com.aidar.auditlogservice.entity.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuditEventRepository extends JpaRepository<AuditEvent, UUID> {
    Optional<AuditEvent> findByEventId(String eventId);
    List<AuditEvent> findByUserIdAndEventType(String userId, String eventType);
}