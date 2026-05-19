package com.paypal.transaction_service.controller;


import com.paypal.transaction_service.dto.TransferRequest;
import com.paypal.transaction_service.entity.Transaction;
import com.paypal.transaction_service.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions") // Base URL
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TransferRequest request) {
       // Transaction created = transactionService.createTransaction(transaction)
        Transaction txn = new Transaction();// create a fresh transaction entity that will be saved to DB
        txn.setSenderId(request.getSenderId());// take Data from (TransferRequest) DTO and put it into Transaction (Entity)
        txn.setReceiverId(request.getReceiverId());
        txn.setAmount(request.getAmount());
        Transaction created = transactionService.createTransaction(txn);
        return ResponseEntity.ok(created); // 201
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }
    @GetMapping ("/all")
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

}
