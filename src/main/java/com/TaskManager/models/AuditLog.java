package com.TaskManager.models;

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
    private String action;
    private String username;
    private LocalDateTime timeStamp;
    private String details;

    public AuditLog(String entityName, String action, String username, String details){
        this.entityName = entityName;
        this.action = action;
        this.username = username;
        this.details = details;
    }

}



