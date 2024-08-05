package com.mangoket.camunda.notification;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void sendNotification(Notification notification);
}
