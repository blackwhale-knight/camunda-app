package com.mangoket.camunda.service;

import com.example.tasklist.ApiException;
import com.example.tasklist.api.TaskApi;
import com.example.tasklist.model.TaskResponse;
import com.example.tasklist.model.TaskSearchRequest;
import com.example.tasklist.model.TaskSearchResponse;
import com.mangoket.camunda.model.Variable;
import io.camunda.common.auth.Authentication;
import io.camunda.common.auth.Product;
import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private ZeebeClient zeebeClient;

    @Autowired
    private TaskApi taskApi;

    @Autowired
    private Authentication authentication;

    public TaskResponse getTask(String taskId) {
        TaskResponse taskResponse = new TaskResponse();
        try {
            taskResponse = taskApi.getTaskById(taskId, getTasklistAuthTokenHeader());
        } catch (ApiException apiException) {
            LOGGER.error(apiException.getLocalizedMessage());
        }
        return taskResponse;
    }

    public List<TaskSearchResponse> searchTasks(String assignee, String taskName, String processId) {
        List<TaskSearchResponse> tasks = new ArrayList<>();
        try {
            tasks = taskApi.searchTasks(
                    new TaskSearchRequest()
                            .state(TaskSearchRequest.StateEnum.CREATED)
                            .taskDefinitionId(taskName)
                            .processInstanceKey(processId)
                    , getTasklistAuthTokenHeader()
            ).stream().filter(
                    task -> {
                        assert task.getAssignee() != null;
                        return task.getAssignee().equals(assignee);
                    }).toList();
        } catch (ApiException apiException) {
            LOGGER.error(apiException.getLocalizedMessage());
        }

        return tasks;
    }

    public void completeTask(String taskId, List<Variable> variableList) {
        Map<String, Object> variables = Optional.ofNullable(variableList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .collect(
                        Collectors.toMap(
                                Variable::getName,
                                Variable::getValue
                        )
                );

        try {
            zeebeClient.newUserTaskCompleteCommand(Long.parseLong(taskId))
                    .variables(variables)
                    .send()
                    .get();
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Variable> getTaskVariables(String taskId) {
        List<Variable> taskVariables = new ArrayList<>();
        try {
            taskVariables = taskApi.searchTaskVariables(taskId, null, getTasklistAuthTokenHeader())
                    .stream()
                    .map(v -> new Variable(v.getName(), v.getValue()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return taskVariables;
    }

    private Map<String, String> getTasklistAuthTokenHeader() {
        return Map.ofEntries(authentication.getTokenHeader(Product.TASKLIST));
    }
}
