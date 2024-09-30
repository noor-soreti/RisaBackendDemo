package org.example.risabackend.repositories;

import org.example.risabackend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByOrderByNotificationIdDesc();
}
