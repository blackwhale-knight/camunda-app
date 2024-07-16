package com.mangoket.camunda.controller;

import com.example.tasklist.model.TaskSearchResponse;
import com.mangoket.camunda.controller.converter.TaskResponseConverter;
import com.mangoket.camunda.controller.request.Decision;
import com.mangoket.camunda.controller.response.SubmitTaskDecisionResponse;
import com.mangoket.camunda.controller.response.TaskResponse;
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
    private TaskResponseConverter taskResponseConverter;
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable String taskId) {
        com.example.tasklist.model.TaskResponse taskResponseFromCamunda = taskService.getTask(taskId);
        TaskResponse taskResponse = taskResponseConverter.toTaskResponse(taskResponseFromCamunda);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping("/tasks/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(
            @RequestParam String assignee,
            @Nullable String taskName,
            @Nullable String processId
    ) {
        List<TaskSearchResponse> taskSearchResponses = taskService.searchTasks(assignee, taskName, processId);
        List<TaskResponse> responses = taskSearchResponses.stream()
                .map(resp -> taskResponseConverter.toTaskResponse(resp))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/tasks/{taskId}")
    public ResponseEntity<SubmitTaskDecisionResponse> submitTaskDecision(@PathVariable String taskId, @RequestBody Decision decision) {
        String decisionValue = decision.getDecisionType().getValue();
        taskService.submitTaskDecision(taskId, decisionValue);

        SubmitTaskDecisionResponse response = new SubmitTaskDecisionResponse();
        response.setTaskId(taskId);
        response.setDecision(decision);
        return ResponseEntity.ok(response);
    }
}
