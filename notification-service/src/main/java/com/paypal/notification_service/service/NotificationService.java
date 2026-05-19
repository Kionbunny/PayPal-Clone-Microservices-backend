package com.paypal.notification_service.service;

import com.paypal.notification_service.dto.Transaction;
import com.paypal.notification_service.entity.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    // define abstract methods here like create user, delete user
    Notification sendNotification(Notification notification);
    List<Notification> getNotificationsByUserId(Long id);
    List<Notification> getAllNotifications();

    void processTransaction(Transaction transaction);
}
