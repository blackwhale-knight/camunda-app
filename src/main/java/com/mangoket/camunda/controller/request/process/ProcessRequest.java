package com.mangoket.camunda.controller.request.process;

import com.mangoket.camunda.model.SourceService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessRequest {
    // Use `processInstanceKey` to identify each request
    private long requestId;
    private String requester;
    private ProcessType processType;
    private SourceService sourceService;
}
