package com.mangoket.camunda.service.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;

public interface BaseJobWorker {
    void handle(JobClient client, ActivatedJob job);
}
