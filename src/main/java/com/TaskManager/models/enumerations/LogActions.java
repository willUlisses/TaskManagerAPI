package com.TaskManager.models.enumerations;

public enum LogActions {

    CREATE("create"),
    DELETE("delete"),
    UPDATE("update");

    private String action;

    LogActions(String action) {
        this.action = action;
    }

}
