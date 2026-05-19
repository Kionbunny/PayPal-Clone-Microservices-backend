package com.paypal.notification_service.service;

import com.paypal.notification_service.repository.NotificationRepository;
import com.paypal.notification_service.entity.Notification;
import com.paypal.notification_service.dto.Transaction;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service // its a Service Class that is managed by Spring
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    @Override
    public void processTransaction(Transaction transaction) {

        Notification notification = new Notification();

        notification.setUserId(transaction.getReceiverId());

        notification.setMessage(
                "💰 ₹" + transaction.getAmount() +
                        " received from user " + transaction.getSenderId()
        );

        notification.setSentAt(LocalDateTime.now());

        notificationRepository.save(notification);

        System.out.println("✅ Notification saved: " + notification);
    }

    @Override
    public Notification sendNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long id) {
         return notificationRepository.findByUserId(id);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
