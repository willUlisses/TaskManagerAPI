package com.TaskManager.models.enumerations;

import lombok.Getter;

@Getter
public enum LogActions {

    CREATE("create"),
    DELETE("delete"),
    UPDATE("update");

    private final String action;

    LogActions(String action) {
        this.action = action;
    }

}
