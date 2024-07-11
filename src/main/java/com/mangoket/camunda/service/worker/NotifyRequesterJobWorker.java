package com.mangoket.camunda.service.worker;

import com.mangoket.camunda.notification.Notification;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;

public class NotifyRequesterJobWorker implements BaseJobWorker {

    protected static final String REQUESTER_FIELD = "requester";
    protected static final String PRODUCT_ID_FIELD = "productId";
    protected static final String NEW_SALE_PRICE_FIELD = "newSalePrice";
    protected static final String OLD_SALE_PRICE_FIELD = "oldSalePrice";

    @Override
    public void handle(JobClient client, ActivatedJob job) {}

    protected Notification composeNotification(ActivatedJob job) {
        String requester = (String) job.getVariablesAsMap().get(REQUESTER_FIELD);
        String productId = (String) job.getVariablesAsMap().get(PRODUCT_ID_FIELD);
        Double newSalePrice = (Double) job.getVariablesAsMap().get(NEW_SALE_PRICE_FIELD);
        Double oldSalePrice = (Double) job.getVariablesAsMap().get(OLD_SALE_PRICE_FIELD);
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
