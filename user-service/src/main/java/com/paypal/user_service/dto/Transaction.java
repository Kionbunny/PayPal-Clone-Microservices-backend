package com.paypal.user_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Transaction {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private Double amount;
    private String timestamp;
    //private LocalDateTime timestamp;
    private String status;
}