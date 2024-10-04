package org.example.risabackend.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity // Tells Hibernate to make a table out of this class
@Table(name = "NOTIFICATION_LOG")
public class NotificationLog {
    @Id
    @GeneratedValue
    private Long notificationLogId;
    @OneToMany
    private List<Notification> notifications;
    // non-owning side of OneToOne relationship
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    // JPA specification requires a no-args constructor
    public NotificationLog() {}

    public NotificationLog(Long notificationLogId, List<Notification> notifications) {
        this.notificationLogId = notificationLogId;
        this.notifications = notifications;
//        this.user = user;
    }

    public Long getNotificationLogId() {
        return notificationLogId;
    }

    public void setNotificationLogId(Long notificationLogId) {
        this.notificationLogId = notificationLogId;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
