package com.mangoket.camunda.controller;

import com.mangoket.camunda.controller.request.ProcessRequest;
import com.mangoket.camunda.controller.response.ProcessResponse;
import com.mangoket.camunda.service.ProcessService;
import io.camunda.operate.model.ProcessInstance;
import io.camunda.operate.model.Variable;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProcessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    private ProcessService processService;

    @PostMapping("/processes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProcessResponse> createProcess(@Valid @RequestBody ProcessRequest request) {
        String processName = request.getProcessType().getProcessName();

        ProcessInstanceEvent processInstance = processService.createProcessInstance(processName, request);
        LOGGER.info("Create a new process: {}. request id: {}", processName, processInstance.getProcessInstanceKey());

        ProcessResponse response = new ProcessResponse();
        response.setId(processInstance.getProcessInstanceKey());
        response.setProcessType(request.getProcessType());
        response.setRequester(request.getRequester());
        response.setSourceService(request.getSourceService());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/processes/{processInstanceKey}")
    public ResponseEntity<ProcessInstance> getProcessInstance(@PathVariable Long processInstanceKey) {
        ProcessInstance processInstance = processService.getProcessInstance(processInstanceKey);
        LOGGER.info("Get Process Instance: processInstanceKey={}", processInstanceKey);
        return ResponseEntity.ok(processInstance);
    }

    @GetMapping("/processes/all")
    public ResponseEntity<List<ProcessInstance>> getAllProcessInstance() {
        List<ProcessInstance> processInstances = processService.getAllProcessInstance();
        LOGGER.info("Get All Process Instances.");
        return ResponseEntity.ok(processInstances);
    }

    @GetMapping("/processes/{processInstanceKey}/variables")
    public ResponseEntity<List<Variable>> getProcessInstanceVariables(@PathVariable String processInstanceKey) {
        List<Variable> variables = processService.getProcessInstanceVariables(processInstanceKey);
        LOGGER.info("Get Process Instance Variables: processInstanceKey={}", processInstanceKey);
        return ResponseEntity.ok(variables);
    }

}
