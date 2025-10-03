package com.TaskManager.models.enumerations;

import lombok.Getter;

@Getter
public enum TaskState {
    INCOMPLETE("Incompleted"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");



    private final String state;

    TaskState(String state) {
        this.state = state;
    }


}
