package com.mangoket.camunda.controller.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangoket.camunda.controller.response.TaskResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

class TaskResponseComposerTest {
    
    private com.example.tasklist.model.TaskResponse srcTaskResponse;
    private com.example.tasklist.model.TaskSearchResponse srcTaskSearchResponse;

    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File taskResponseFile = new File(
                Objects.requireNonNull(this.getClass().getClassLoader().getResource("sample-task-response.json")).getFile()
        );

        File taskSearchResponseFile = new File(
                Objects.requireNonNull(this.getClass().getClassLoader().getResource("sample-task-search-response.json")).getFile()
        );
        srcTaskResponse = mapper.readValue(taskResponseFile, com.example.tasklist.model.TaskResponse.class);
        srcTaskSearchResponse = mapper.readValue(taskSearchResponseFile, com.example.tasklist.model.TaskSearchResponse.class);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testComposeTaskResponseByTaskResponse() {
        TaskResponseComposer composer = new TaskResponseComposer();
        TaskResponse result = composer.composeTaskResponse(srcTaskResponse);

        Assertions.assertEquals(result.getTaskId(), srcTaskResponse.getId());
        Assertions.assertEquals(result.getName(), srcTaskResponse.getName());
        Assertions.assertEquals(result.getTaskDefinitionId(), srcTaskResponse.getTaskDefinitionId());
        Assertions.assertEquals(result.getProcessName(), srcTaskResponse.getProcessName());
        Assertions.assertEquals(result.getAssignee(), srcTaskResponse.getAssignee());
        assert srcTaskResponse.getTaskState() != null;
        Assertions.assertEquals(result.getTaskState().getValue(), srcTaskResponse.getTaskState().getValue());
        Assertions.assertEquals(result.getProcessId(), srcTaskResponse.getProcessInstanceKey());
        Assertions.assertEquals(result.getCreationDate(), srcTaskResponse.getCreationDate());
    }

    @Test
    void testComposeTaskResponseByTaskSearchResponse() {
        TaskResponseComposer composer = new TaskResponseComposer();
        TaskResponse result = composer.composeTaskResponse(srcTaskSearchResponse);

        Assertions.assertEquals(result.getTaskId(), srcTaskSearchResponse.getId());
        Assertions.assertEquals(result.getName(), srcTaskSearchResponse.getName());
        Assertions.assertEquals(result.getTaskDefinitionId(), srcTaskSearchResponse.getTaskDefinitionId());
        Assertions.assertEquals(result.getProcessName(), srcTaskSearchResponse.getProcessName());
        Assertions.assertEquals(result.getAssignee(), srcTaskSearchResponse.getAssignee());
        assert srcTaskSearchResponse.getTaskState() != null;
        Assertions.assertEquals(result.getTaskState().getValue(), srcTaskSearchResponse.getTaskState().getValue());
        Assertions.assertEquals(result.getProcessId(), srcTaskSearchResponse.getProcessInstanceKey());
        Assertions.assertEquals(result.getCreationDate(), srcTaskSearchResponse.getCreationDate());
    }
}