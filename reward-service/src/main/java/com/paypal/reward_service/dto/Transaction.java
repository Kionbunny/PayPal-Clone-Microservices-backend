package com.paypal.reward_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
//@Data -> getters, setters, toString(), equals(), hashCode()
public class Transaction {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private Double amount;
  //  private String timestamp;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String status;
}