package com.paypal.transaction_service.service;
import com.paypal.transaction_service.entity.Transaction;
import com.paypal.transaction_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import com.paypal.transaction_service.kafka.KafkaEventProducer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final KafkaEventProducer kafkaEventProducer;

    private final TransactionRepository repository;
   // private final ObjectMapper objectMapper ;


    public TransactionServiceImpl(KafkaEventProducer kafkaEventProducer, TransactionRepository repository) {
        this.kafkaEventProducer = kafkaEventProducer;
        this.repository = repository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setTimestamp(LocalDateTime.now());// time stamp is generated in Service layer
        transaction.setStatus("SUCCESS");// status message is also added in Service layer
        Transaction saved = repository.save(transaction);
        // 🔥 Kafka integration (NEW)
        String key = String.valueOf(saved.getId());
        kafkaEventProducer.sendTransactionEvent(key, saved);
        return saved;
//  return repository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found" + id));
    }

    @Override
    public List<Transaction> getAllTransactions() {
       // Scanner transactionRepository;
        return repository.findAll();
    }
}
