package org.example.risabackend.persistence.repositories;

import org.example.risabackend.persistence.entity.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {
//    List<NotificationLog> orderInDescending(NotificationLog notificationLog);
//    List<NotificationLog> orderInAscending(NotificationLog notificationLog);
}