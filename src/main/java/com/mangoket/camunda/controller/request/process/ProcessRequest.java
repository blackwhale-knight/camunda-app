package com.mangoket.camunda.controller.request.process;

import com.mangoket.camunda.model.SourceService;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ProcessRequest {
    // Use `processInstanceKey` to identify each request
    private long requestId;
    @NonNull
    private String requester;
    @NonNull
    private ProcessType processType;
    @NonNull
    private SourceService sourceService;
}
