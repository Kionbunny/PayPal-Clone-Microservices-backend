package com.paypal.notification_service.controller;

import com.paypal.notification_service.entity.Notification;
import com.paypal.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    //Transaction Service → Kafka → Notification Service
    /*
    Transaction happens ✅
    Kafka event sent ✅
    NotificationConsumer picks it ✅
    Notification saved in DB ✅
     */
    @PostMapping
    public Notification sendNotification(@RequestBody Notification notification) {
        return notificationService.sendNotification(notification);
    } // about method is optional bcz notifications are auto-created by kafka

    @GetMapping("/{userId}")
    public List<Notification> getNotificationsByUser(@PathVariable Long userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @GetMapping("/all-notifications")
    public List<Notification>getAllNotifications(){
        return notificationService.getAllNotifications();
    }
}