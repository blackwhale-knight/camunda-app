package com.mangoket.camunda.service.worker;

import com.mangoket.camunda.notification.Notification;
import com.mangoket.camunda.notification.NotificationService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifyRequesterRejectionJobWorker extends NotifyRequesterJobWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyRequesterRejectionJobWorker.class);

    @Autowired
    NotificationService notificationService;

    @Override
    @JobWorker(type = "notifyRequesterRejection")
    public void handle(JobClient client, ActivatedJob job) {
        Notification notification = composeNotification(job);
        notificationService.sendNotification(notification);
        LOGGER.info("[REJECTED] Notify Requester: {} about the rejection.", notification.getRequester());
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
