package com.mangoket.camunda.service.worker;

import com.mangoket.camunda.notification.Notification;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;

public class NotifyRequesterJobWorker implements BaseJobWorker {
    @Override
    public void handle(JobClient client, ActivatedJob job) {}

    protected Notification composeNotification(ActivatedJob job) {
        String requester = (String)job.getVariablesAsMap().get("requester");
        String productId = (String)job.getVariablesAsMap().get("productId");
        Double newSalePrice = (Double)job.getVariablesAsMap().get("newSalePrice");
        Double oldSalePrice = (Double)job.getVariablesAsMap().get("oldSalePrice");
        long processId = job.getProcessInstanceKey();

        Notification notification = new Notification();
        notification.setRequester(requester);
        notification.setProductId(productId);
        notification.setNewSalePrice(newSalePrice);
        notification.setOldSalePrice(oldSalePrice);
        notification.setProcessId(processId);

        return notification;
    }
}
