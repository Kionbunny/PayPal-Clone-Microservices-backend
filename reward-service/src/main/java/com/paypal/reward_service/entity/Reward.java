package com.paypal.reward_service.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
 // this User class is stored as Table in DB
@Getter // lombok manages getters & setters for our fields
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Entity
@Table(name="app_reward")
public class Reward {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;  // to prevent duplicate rewards
    private Long userId;
    private Double points;

    private LocalDateTime sentAt; // when was this reward created? automatically generated 

    @Column(unique = true)
    private Long transactionId;
}
