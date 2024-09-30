package org.example.risabackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "MESSAGE")
public class Message {
    @Id
    @GeneratedValue
    private Long messageId;
    private String message;
    private Long senderId;
    private Long receiverId;
    private Long deliveredAt;
    @ManyToOne
    @JoinColumn(name = "chatLogId", nullable = false)
    private ChatLog chatLog;
    private boolean readAt;

    // JPA specification requires a no-args constructor
    public Message() {}

    public Message(Long messageId, String message, Long senderId, Long receiverId, Long deliveredAt, ChatLog chatLog, boolean readAt) {
        this.messageId = messageId;
        this.message = message;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.deliveredAt = deliveredAt;
        this.chatLog = chatLog;
        this.readAt = readAt;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getreceiverId() {
        return receiverId;
    }

    public void setreceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getdeliveredAt() {
        return deliveredAt;
    }

    public void setdeliveredAt(Long deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public ChatLog getChatLog() {
        return chatLog;
    }

    public void setChatLog(ChatLog chatLog) {
        this.chatLog = chatLog;
    }

    public boolean isreadAt() {
        return readAt;
    }

    public void setreadAt(boolean readAt) {
        this.readAt = readAt;
    }
}
