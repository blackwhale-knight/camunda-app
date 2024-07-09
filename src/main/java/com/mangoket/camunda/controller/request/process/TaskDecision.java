package com.mangoket.camunda.controller.request.process;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDecision {
    private String taskId;
    private DecisionType decisionType;
}
