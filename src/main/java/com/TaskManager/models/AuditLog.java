package com.TaskManager.models;

import com.TaskManager.models.enumerations.LogActions;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityName;
    private Long entityId;

    @Enumerated(EnumType.STRING)
    private LogActions action;

    private String username;
    private LocalDateTime timeStamp = LocalDateTime.now();
    private String details;
}



