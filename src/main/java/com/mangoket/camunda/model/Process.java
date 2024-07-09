package com.mangoket.camunda.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Process {
    private long id;
    private String name;
    private String requester;
    private SourceService sourceService;
    private ProcessState status;
    private Date startDate;
    private Date endDate;

    public Process(long id, String name, String requester, SourceService sourceService) {
        this.id = id;
        this.name = name;
        this.requester = requester;
        this.sourceService = sourceService;
    }
}
