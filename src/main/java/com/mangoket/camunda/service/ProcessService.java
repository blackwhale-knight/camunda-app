package com.mangoket.camunda.service;

import com.mangoket.camunda.controller.request.ProcessRequest;
import com.mangoket.camunda.model.VariableSearchFilter;
import io.camunda.operate.CamundaOperateClient;
import io.camunda.operate.model.ProcessInstance;
import io.camunda.operate.model.Variable;
import io.camunda.operate.search.SearchQuery;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {
    private static final Integer MAX_SEARCH_SIZE = 1000;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessService.class);

    @Autowired
    private ZeebeClient zeebeClient;

    @Autowired
    private CamundaOperateClient camundaOperateClient;

    public ProcessInstanceEvent createProcessInstance(String processName, ProcessRequest request) {
        return this.zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(processName)
                .latestVersion()
                .variables(request)
                .send()
                .join();
    }

    public ProcessInstance getProcessInstance(Long processInstanceKey) {
        try {
            return camundaOperateClient.getProcessInstance(processInstanceKey);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<ProcessInstance> getAllProcessInstance() {
        try {
            SearchQuery searchQuery = new SearchQuery();
            searchQuery.setSize(MAX_SEARCH_SIZE);

            return camundaOperateClient.searchProcessInstances(searchQuery);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Variable> getProcessInstanceVariables(String processInstanceKey) {
        try {
            VariableSearchFilter filter = new VariableSearchFilter();
            filter.setProcessInstanceKey(processInstanceKey);

            SearchQuery searchQuery = new SearchQuery();
            searchQuery.setFilter(filter);

            return camundaOperateClient.searchVariables(searchQuery);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
