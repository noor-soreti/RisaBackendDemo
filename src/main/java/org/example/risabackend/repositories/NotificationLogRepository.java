package org.example.risabackend.repositories;

import org.example.risabackend.models.NotificationLog;
import org.example.risabackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {
//    List<NotificationLog> orderInDescending(NotificationLog notificationLog);
//    List<NotificationLog> orderInAscending(NotificationLog notificationLog);
}