package com.mangoket.camunda.service;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProcessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessService.class);

    @Autowired
    private ZeebeClient zeebeClient;
    public ProcessInstanceEvent createProcessInstance(String processName, Map<String, Object> variables) {
        return this.zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(processName)
                .latestVersion()
                .variables(variables)
                .send()
                .join();
    }
}
