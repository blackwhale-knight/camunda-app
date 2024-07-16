package com.mangoket.camunda.controller;

import com.mangoket.camunda.controller.helper.ProcessVariablesAssembler;
import com.mangoket.camunda.controller.request.UpdateProductPriceProcessRequest;
import com.mangoket.camunda.controller.response.ProcessResponse;
import com.mangoket.camunda.service.ProcessService;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/processes")
public class ProcessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    private ProcessService processService;

    @Autowired
    private ProcessVariablesAssembler processVariablesAssembler;

    @PostMapping("/update-product-price")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProcessResponse> createUpdateProductPriceProcess(@Valid @RequestBody UpdateProductPriceProcessRequest request) {
        String processName = request.getProcessType().getProcessName();
        Map<String, Object> processVariables
                = processVariablesAssembler.assembleUpdateProductPriceProcessVariables(request);

        ProcessInstanceEvent processInstance = processService.createProcessInstance(processName, processVariables);

        ProcessResponse response = new ProcessResponse();
        response.setId(processInstance.getProcessInstanceKey());
        response.setProcessType(request.getProcessType());
        response.setRequester(request.getRequester());
        response.setSourceService(request.getSourceService());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{processId}")
    public ResponseEntity<ProcessResponse> getProcess(@PathVariable long processId) {
        ProcessResponse response = new ProcessResponse();
        response.setId(processId);
        return ResponseEntity.ok(response);
    }

}
