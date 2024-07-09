package com.mangoket.camunda.sample;

import com.example.tasklist.model.*;
import io.camunda.common.auth.Authentication;
import com.example.tasklist.api.TaskApi;
import io.camunda.common.auth.Product;
import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

@Service
public class UserTasksManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTasksManagementService.class);

    @Autowired private ZeebeClient zeebe;

    @Autowired private TaskApi taskApi;

    @Autowired private Authentication authentication;


//    @EventListener(ApplicationReadyEvent.class)
    public void manageUserTasks(long processInstanceKey) throws Exception {
        // 1. Start a process instance
//        long processInstanceKey =
//                zeebe
//                        .newCreateInstanceCommand()
//                        .bpmnProcessId("Process_UpdateProductPrice")
//                        .latestVersion()
//                        .send()
//                        .get()
//                        .getProcessInstanceKey();
//        LOG.info("Started a 'api-test-process' process instance with key={}", processInstanceKey);

        // 2. Search for user tasks (filter by processInstanceKey, implementation=ZEEBE_USER_TASK,
        // state=CREATED and sort DESC by creationTime)


        List<TaskSearchResponse> tasks =
                waitFor(
                        () ->
                                taskApi.searchTasks(
                                        new TaskSearchRequest()
                                                .state(TaskSearchRequest.StateEnum.CREATED)
                                                .implementation(TaskSearchRequest.ImplementationEnum.ZEEBE_USER_TASK)
                                                .processInstanceKey(String.valueOf(processInstanceKey))
                                                .sort(
                                                        List.of(
                                                                new TaskOrderBy()
                                                                        .field(TaskOrderBy.FieldEnum.CREATIONTIME)
                                                                        .order(TaskOrderBy.OrderEnum.DESC))),
                                        getTasklistAuthTokenHeader()),
                        taskSearchResponses -> !taskSearchResponses.isEmpty());
        LOGGER.info("Found {} task(s) in Tasklist", tasks.size());

        // 3. Pick the last created task
        String taskId = tasks.get(0).getId();
        LOGGER.info("The chosen task id={}", taskId);

        // 4. Assign task to "demo" user
        zeebe.newUserTaskAssignCommand(Long.parseLong(taskId)).assignee("cody.huang@mangoket.com").send().get();

        // 5. Get the task by id
        TaskResponse taskResponse =
                waitFor(
                        () -> taskApi.getTaskById(taskId, getTasklistAuthTokenHeader()),
                        response -> "cody.huang@mangoket.com".equals(response.getAssignee()));
        LOGGER.info("Task {} is assigned to {}", taskId, taskResponse.getAssignee());

        // 6. Complete the task
        Map<String, Object> variables = new HashMap<>();
        variables.put("decision", "approved");
        zeebe.newUserTaskCompleteCommand(Long.parseLong(taskId)).variables(variables).send().get();

        // 7. Get the task and check the state
        taskResponse =
                waitFor(
                        () -> taskApi.getTaskById(taskId, getTasklistAuthTokenHeader()),
                        response -> response.getTaskState() == TaskResponse.TaskStateEnum.COMPLETED);
        LOGGER.info("Task {} has {} state", taskId, taskResponse.getTaskState());
    }

    private Map<String, String> getTasklistAuthTokenHeader() {
        return Map.ofEntries(authentication.getTokenHeader(Product.TASKLIST));
    }

    private <T> T waitFor(Callable<T> responseSupplier, Predicate<T> responseTester)
            throws Exception {
        int maxRounds = 10;
        int waitRound = 0;
        int waitTime = 1000;
        while (waitRound < maxRounds) {
            T response = responseSupplier.call();
            if (responseTester.test(response)) {
                return response;
            }
            Thread.sleep(waitTime);
            waitRound++;
        }
        throw new RuntimeException(
                String.format("Test is not successful after %s attempts", waitRound));
    }
}