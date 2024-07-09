package com.mangoket.camunda.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProcessesResponse {
    List<ProcessResponse> processResponses;
}
