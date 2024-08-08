package com.mangoket.camunda.model;

import io.camunda.operate.search.Filter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariableSearchFilter implements Filter {
    private String processInstanceKey;
}
