package org.example.risabackend.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity // Tells Hibernate to make a table out of this class
@Table(name = "NOTIFICATION_LOG")
public class NotificationLog {
    @Id
    @GeneratedValue
    @Column(name = "notification_log_id")
    private Long id;
    @OneToMany
    private List<Notification> notifications;
    // non-owning side of OneToOne relationship
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    // JPA specification requires a no-args constructor
    public NotificationLog() {}

    public NotificationLog(List<Notification> notifications) {
        this.notifications = notifications;
//        this.user = user;
    }

}
