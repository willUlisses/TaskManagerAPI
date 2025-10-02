package com.TaskManager.models;

public enum TaskState {
    INCOMPLETE("Incompleted"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");



    private String state;

    TaskState(String state) {
        this.state = state;
    }
}
