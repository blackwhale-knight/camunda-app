package com.mangoket.camunda.controller;

import com.example.tasklist.model.TaskResponse;
import com.example.tasklist.model.TaskSearchResponse;
import com.mangoket.camunda.controller.request.process.Decision;
import com.mangoket.camunda.controller.response.SubmitTaskDecisionResponse;
import com.mangoket.camunda.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable String taskId) {
        TaskResponse response = taskService.getTask(taskId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tasks/search")
    public ResponseEntity<List<TaskSearchResponse>> searchTasks(
            @RequestParam String assignee,
            @Nullable String taskName,
            @Nullable String processId
    ) {
        List<TaskSearchResponse> response = taskService.searchTasks(assignee, taskName, processId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/tasks/{taskId}")
    public ResponseEntity<SubmitTaskDecisionResponse> submitTaskDecision(@PathVariable String taskId, @RequestBody Decision decision) {
        String decisionValue = decision.getDecisionType().getStrValue();
        taskService.submitTaskDecision(taskId, decisionValue);
        SubmitTaskDecisionResponse response = new SubmitTaskDecisionResponse();
        response.setTaskId(taskId);
        response.setDecision(decision);
        return ResponseEntity.ok(response);
    }
}
