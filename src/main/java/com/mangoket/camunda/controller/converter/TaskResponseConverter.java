package com.mangoket.camunda.controller.converter;

import com.mangoket.camunda.controller.response.TaskResponse;
import com.mangoket.camunda.model.TaskState;
import org.springframework.stereotype.Service;

@Service
public class TaskResponseConverter {
    public TaskResponse toTaskResponse(com.example.tasklist.model.TaskResponse src) {
        TaskResponse target = new TaskResponse();
        target.setTaskId(src.getId());
        target.setName(src.getName());
        target.setTaskDefinitionId(src.getTaskDefinitionId());
        target.setProcessName(src.getProcessName());
        target.setAssignee(src.getAssignee());
        assert src.getTaskState() != null;
        target.setTaskState(TaskState.valueOf(src.getTaskState().getValue()));
        target.setProcessId(src.getProcessInstanceKey());
        target.setCreationDate(src.getCreationDate());
        return target;
    }

    public TaskResponse toTaskResponse(com.example.tasklist.model.TaskSearchResponse src) {
        TaskResponse target = new TaskResponse();
        target.setTaskId(src.getId());
        target.setName(src.getName());
        target.setTaskDefinitionId(src.getTaskDefinitionId());
        target.setProcessName(src.getProcessName());
        target.setAssignee(src.getAssignee());
        assert src.getTaskState() != null;
        target.setTaskState(TaskState.valueOf(src.getTaskState().getValue()));
        target.setProcessId(src.getProcessInstanceKey());
        target.setCreationDate(src.getCreationDate());
        return target;
    }
}
