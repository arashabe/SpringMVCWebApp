package com.spring.ums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ums.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientEmail(String recipientEmail);
    List<Notification> findByRecipientEmailOrSenderEmailAndStatus(String recipientEmail, String senderEmail, String status);
}
