package org.example.risabackend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    private String status;
    private Long lastSeen;
    private boolean isOnline;
    // collections are lazy-loaded by default, need to specify fetch
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ChatLog> chatLogs;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Notification> notifications;

    // JPA specification requires a no-args constructor
    public User() {}

    public User(String fullName, String email, String phoneNumber, String profilePicture, String status) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.status = status;
        this.lastSeen = System.currentTimeMillis();
        this.isOnline = true;
        this.chatLogs = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public List<ChatLog> getChatLogs() {
        return chatLogs;
    }

    public void setChatLogs(List<ChatLog> chatLogs) {
        this.chatLogs = chatLogs;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId = " + userId +
                ", fullName = '" + fullName + '\'' +
                ", email = '" + email + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", profilePicture = '" + profilePicture + '\'' +
                ", status = '" + status + '\'' +
                ", lastSeen = " + lastSeen +
                ", isOnline = " + isOnline +
                ", chatLogs = " + chatLogs +
                ", notifications =" + notifications.toString() +
                '}';
    }
}
