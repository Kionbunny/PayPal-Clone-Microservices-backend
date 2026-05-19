package com.paypal.user_service.kafka;

import com.paypal.user_service.dto.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventConsumer {

    @KafkaListener(topics = "txn-initiated", groupId = "user-service-group")
    public void consume(Transaction transaction) {

        System.out.println("📥 Received Transaction Event:");
        System.out.println("Sender: " + transaction.getSenderId());
        System.out.println("Receiver: " + transaction.getReceiverId());
        System.out.println("Amount: " + transaction.getAmount());
        System.out.println("Status: " + transaction.getStatus());
        System.out.println(transaction);

        //  Here you can:
        // - Update user balance
        // - Send notification
        // - Store audit logs
    }
}