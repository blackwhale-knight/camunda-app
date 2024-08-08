package com.mangoket.camunda.controller;

import com.example.tasklist.model.TaskSearchResponse;
import com.mangoket.camunda.controller.helper.TaskResponseComposer;
import com.mangoket.camunda.controller.response.CompleteTaskResponse;
import com.mangoket.camunda.controller.response.TaskResponse;
import com.mangoket.camunda.model.Variable;
import com.mangoket.camunda.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskResponseComposer taskResponseComposer;
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable String taskId) {
        com.example.tasklist.model.TaskResponse taskResponseFromCamunda = taskService.getTask(taskId);
        LOGGER.info("Get task: id={}", taskId);

        TaskResponse taskResponse = taskResponseComposer.composeTaskResponse(taskResponseFromCamunda);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping("/tasks/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(
            @RequestParam String assignee,
            @Nullable String taskName,
            @Nullable String processId
    ) {
        List<TaskSearchResponse> taskSearchResponses = taskService.searchTasks(assignee, taskName, processId);
        LOGGER.info("Search tasks: assignee={}, taskName={}, processId={}", assignee, taskName, processId);

        List<TaskResponse> responses = taskSearchResponses.stream()
                .map(resp -> taskResponseComposer.composeTaskResponse(resp))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/tasks/{taskId}")
    public ResponseEntity<CompleteTaskResponse> completeTask(@PathVariable String taskId, @Nullable @RequestBody List<Variable> variableList) {
        taskService.completeTask(taskId, variableList);
        LOGGER.info("Complete task: taskId={}, with variables={}", taskId, variableList);

        CompleteTaskResponse response = new CompleteTaskResponse();
        response.setTaskId(taskId);
        response.setVariables(variableList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tasks/{taskId}/variables")
    public ResponseEntity<List<Variable>> getTaskVariables(@PathVariable String taskId) {
        List<Variable> response = taskService.getTaskVariables(taskId);
        LOGGER.info("Get task variables: taskId={}", taskId);
        return ResponseEntity.ok(response);
    }

}
