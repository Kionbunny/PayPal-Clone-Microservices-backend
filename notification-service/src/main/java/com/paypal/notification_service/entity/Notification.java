package com.paypal.notification_service.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity // this User class is stored as Table in DB
@Getter // lombok manages getters & setters for our fields
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="app_notification")
public class Notification {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String message;
    private LocalDateTime sentAt;
}
