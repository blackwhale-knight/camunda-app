package com.mangoket.camunda.model;

import com.example.tasklist.model.TaskResponse;
import com.example.tasklist.model.TaskSearchResponse;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TaskState {
    CREATED("CREATED", TaskResponse.TaskStateEnum.CREATED, TaskSearchResponse.TaskStateEnum.CREATED),

    COMPLETED("COMPLETED", TaskResponse.TaskStateEnum.COMPLETED, TaskSearchResponse.TaskStateEnum.COMPLETED),

    CANCELED("CANCELED", TaskResponse.TaskStateEnum.CANCELED, TaskSearchResponse.TaskStateEnum.CANCELED),

    FAILED("FAILED", TaskResponse.TaskStateEnum.FAILED, TaskSearchResponse.TaskStateEnum.FAILED);

    private final String value;
    private final TaskResponse.TaskStateEnum taskResponseTaskState;
    private final TaskSearchResponse.TaskStateEnum taskSearchResponseTaskState;

    TaskState(String value,
              TaskResponse.TaskStateEnum taskResponseTaskState,
              TaskSearchResponse.TaskStateEnum taskSearchResponseTaskState
    ) {
        this.value = value;
        this.taskResponseTaskState = taskResponseTaskState;
        this.taskSearchResponseTaskState = taskSearchResponseTaskState;
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
