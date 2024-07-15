package com.mangoket.camunda.controller.request.process;

import com.mangoket.camunda.model.SourceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Valid
public class ProcessRequest {
    @Email(message = "the requester must be in email format")
    @NotEmpty(message = "the requester is required")
    private String requester;

    @NotNull(message = "the process type is required")
    private ProcessType processType;

    @NotNull(message = "the source service is required")
    private SourceService sourceService;
}
