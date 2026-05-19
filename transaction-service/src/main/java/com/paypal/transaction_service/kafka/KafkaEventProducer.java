package com.paypal.transaction_service.kafka;

import com.paypal.transaction_service.entity.Transaction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//Spring creates a bean of this class
@Component
public class KafkaEventProducer {

    private static final String TOPIC = "txn-initiated";

    // Standard KafkaTemplate provided by Spring
    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    /*
    String -> Key, Transaction -> value (data)
    Key: "txn-1", Value: { senderId, receiverId, amount, ... }
     */

    public KafkaEventProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //below method is called from Service Implementation
    public void sendTransactionEvent(String key, Transaction transaction) {
        System.out.println("📤 Sending Transaction: " + key);

        // We use the modern CompletableFuture approach (Standard in Spring Boot 3/4)
        kafkaTemplate.send(TOPIC, key, transaction)
                .thenAccept(result -> {
                    var metadata = result.getRecordMetadata();
                    System.out.println("✅ Sent to Partition: " + metadata.partition() + " at Offset: " + metadata.offset());
                })
                .exceptionally(ex -> {
                    System.err.println("❌ Kafka Error: " + ex.getMessage());
                    return null;
                });
    }
}