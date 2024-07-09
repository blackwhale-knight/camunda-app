package com.mangoket.camunda.controller.response;

import com.mangoket.camunda.model.ProcessState;
import com.mangoket.camunda.model.SourceService;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ProcessResponse {
    private long id;
    private String name;
    private String requester;
    private SourceService sourceService;
    private ProcessState status;
    private Date startDate;
    private Date endDate;
}
