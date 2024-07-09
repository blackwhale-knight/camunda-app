package com.mangoket.camunda.service.worker;

import com.mangoket.camunda.notification.Notification;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotifyRequesterApprovalJobWorker extends NotifyRequesterJobWorker {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyRequesterApprovalJobWorker.class);
    @Override
    @JobWorker(type = "notifyRequesterApproval")
    public void handle(JobClient client, ActivatedJob job) {
        Notification notification = composeNotification(job);
        // TODO: Implement NotificationService.notifyRequesterApproval
        // notificationService.notifyRequesterApproval(requester, productId, newSalePrice, oldSalePrice, job.getProcessInstanceKey());
        LOGGER.info("[APPROVED] Notify Requester: {} about the approval.", notification.getRequester());
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
