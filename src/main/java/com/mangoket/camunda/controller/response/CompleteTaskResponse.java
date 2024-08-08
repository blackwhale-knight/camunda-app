package com.mangoket.camunda.controller.response;

import com.mangoket.camunda.model.Variable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompleteTaskResponse {
    private String taskId;
    private List<Variable> variables;
}
