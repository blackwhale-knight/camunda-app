package com.mangoket.camunda.controller;

import com.mangoket.camunda.controller.request.process.UpdateProductPriceProcessRequest;
import com.mangoket.camunda.controller.helper.ProcessVariablesAssembler;
import com.mangoket.camunda.service.ProcessService;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProcessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    private ProcessService processService;

    @PostMapping("/processes/update-product-price")
    public long createUpdateProductPriceProcess(@RequestBody UpdateProductPriceProcessRequest request) {
        String processName = request.getProcessType().getProcessName();
        Map<String, Object> processVariables
                = ProcessVariablesAssembler.assembleUpdateProductPriceProcessVariables(request);

        ProcessInstanceEvent processInstance = processService.createProcessInstance(processName, processVariables);
        return processInstance.getProcessInstanceKey();
    }

//    @GetMapping("/processes/{processId}")
//    public ProcessResponse getProcess(@PathVariable long processId) throws OperateException {
//        ProcessInstance processInstance = camundaOperateClient.getProcessInstance(processId);
//        System.out.println(processInstance.getState());
//        return new ProcessResponse();
//    }


}
