package com.TaskManager.services;

import com.TaskManager.models.AuditLog;
import com.TaskManager.repositories.AuditLogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public List<AuditLog> findAll(){
        return repository.findAll();
    }

    public void log(String entityName, String action, String details){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setUsername(username);
        log.setDetails(details);
        log.setTimeStamp(LocalDateTime.now());
        log.setEntityName(entityName);

        repository.save(log);
    }


    // add findByEntityName

    // add findByAction
}
