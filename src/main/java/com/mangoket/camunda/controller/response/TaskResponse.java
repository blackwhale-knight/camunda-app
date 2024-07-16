package com.mangoket.camunda.controller.response;

import com.mangoket.camunda.model.TaskState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
    private String taskId;
    private String name;
    private String taskDefinitionId;
    private String processName;
    private String assignee;
    private TaskState taskState;
    private String processId;
    private String creationDate;
}
