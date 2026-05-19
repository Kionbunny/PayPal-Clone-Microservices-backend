package com.paypal.notification_service.kafka;

import com.paypal.notification_service.dto.Transaction;
import com.paypal.notification_service.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "txn-initiated", groupId = "notification-service-group")
    public void consume(Transaction transaction) {

        System.out.println("📥 Received transaction: " + transaction);

        // Delegate to service layer
        notificationService.processTransaction(transaction);
    }
}