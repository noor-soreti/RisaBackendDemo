package org.example.risabackend.persistence.entity;

import jakarta.persistence.*;

@Entity // Tells Hibernate to make a table out of this class
@Table(name = "NOTIFICATIONS")
public class Notification {
    @Id
    @GeneratedValue
    @Column(name = "notification_id")
    private Long notificationId;
    private String notificationType;
    private String title;
    private String content;
    private Long createdAt;
    private boolean seen;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private NotificationLog notificationLog;

    // JPA specification requires a no-args constructor
    public Notification() {}

    public Notification(String notificationType, String title, String content, NotificationLog notificationLog) {
        this.notificationType = notificationType;
        this.title = title;
        this.content = content;
        this.createdAt = System.currentTimeMillis();
        this.seen = false;
        this.notificationLog = notificationLog;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }



    @Override
    public String toString() {
        return "Notification{" +
                "notificationId = " + notificationId +
                ", notificationType = '" + notificationType + '\'' +
                ", title = '" + title + '\'' +
                ", content = '" + content + '\'' +
                ", createdAt = " + createdAt +
                ", seen = " + seen +
                ", notificationLog = " + notificationLog +
                '}';
    }
}
