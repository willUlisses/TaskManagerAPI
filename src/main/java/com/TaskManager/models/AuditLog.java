package com.TaskManager.models;

import com.TaskManager.models.enumerations.LogActions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityName;
    private Long entityId;

    @Enumerated(EnumType.STRING)
    private LogActions action;

    private String username;
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private String details;
}



