package org.example.risabackend.persistence.repositories;

import org.example.risabackend.persistence.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByOrderByNotificationIdDesc();
}
