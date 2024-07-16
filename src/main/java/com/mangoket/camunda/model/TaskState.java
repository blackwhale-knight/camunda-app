package com.mangoket.camunda.model;

import com.example.tasklist.model.TaskResponse;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TaskState {
    CREATED("CREATED", TaskResponse.TaskStateEnum.CREATED),

    COMPLETED("COMPLETED", TaskResponse.TaskStateEnum.COMPLETED),

    CANCELED("CANCELED", TaskResponse.TaskStateEnum.CANCELED),

    FAILED("FAILED", TaskResponse.TaskStateEnum.FAILED);

    private final String value;
    private final TaskResponse.TaskStateEnum taskStateEnum;

    TaskState(String value, TaskResponse.TaskStateEnum taskStateEnum) {

        this.value = value;
        this.taskStateEnum = taskStateEnum;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
