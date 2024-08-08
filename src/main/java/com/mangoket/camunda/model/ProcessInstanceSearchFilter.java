package com.mangoket.camunda.model;

import io.camunda.operate.search.Filter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessInstanceSearchFilter implements Filter {
    private String key;
    private String startDate;
    private String endDate;
    private String state;
}
