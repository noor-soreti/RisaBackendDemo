package org.example.risabackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "NOTIFICATIONS")
public class Notification {
    @Id
    @GeneratedValue
    private Long notificationId;
    private String notificationType;
    private String title;
    private String content;
    private Long createdAt;
    private boolean seen;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    // JPA specification requires a no-args constructor
    public Notification() {}

    public Notification(String notificationType, String title, String content, User user) {
        this.notificationType = notificationType;
        this.title = title;
        this.content = content;
        this.createdAt = System.currentTimeMillis();
        this.seen = false;
        this.user = user;
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

    public User getUser1() {
        return user;
    }

    public void setUser1(User user1) {
        this.user = user1;
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
                ", user = " + user +
                '}';
    }
}
