package com.mangoket.camunda.controller;

import com.example.tasklist.model.TaskResponse;
import com.example.tasklist.model.TaskSearchResponse;
import com.mangoket.camunda.controller.request.process.TaskDecision;
import com.mangoket.camunda.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;
    @GetMapping("/tasks/{taskId}")
    public TaskResponse getTask(@PathVariable String taskId) {
        return taskService.getTask(taskId);
    }

    @GetMapping("/tasks/search")
    public List<TaskSearchResponse> searchTasks(@RequestParam String assignee, @Nullable String taskName) {
        return taskService.searchTasks(assignee, taskName);
    }

    @PatchMapping("/tasks/{taskId}")
    public void submitDecision(@RequestBody TaskDecision taskDecision) {
        taskService.submitTaskDecision(taskDecision);
    }

}
