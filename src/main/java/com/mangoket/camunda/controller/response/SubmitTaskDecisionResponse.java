package com.mangoket.camunda.controller.response;

import com.mangoket.camunda.controller.request.process.Decision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitTaskDecisionResponse {
    private String taskId;
    private Decision decision;
}
