package com.paypal.transaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // (getters, setters, toString, equals, hashCode)
@NoArgsConstructor //(Constructor without fields)
@AllArgsConstructor // (Constructor with fields)
public class TransferRequest {
    private Long senderId;
    private Long receiverId;
    private Double amount;
}
