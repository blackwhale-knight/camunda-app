package com.mangoket.camunda.controller.response;

import com.mangoket.camunda.model.ProcessState;
import com.mangoket.camunda.model.SourceService;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ProcessResponse {
    @NotBlank
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String requester;

    @NotBlank
    private SourceService sourceService;

    private ProcessState status;
    private Date startDate;
    private Date endDate;
}
