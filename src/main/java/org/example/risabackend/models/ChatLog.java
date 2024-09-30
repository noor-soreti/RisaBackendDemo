package org.example.risabackend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CHAT_LOG")
public class ChatLog {
    @Id
    @GeneratedValue
    private Long chatLogId;
    @ManyToMany
    private List<User> users;
    // One ChatLog can have many Messages
    @OneToMany(mappedBy = "chatLog", cascade = CascadeType.ALL)
    private List<Message> messages;

    // JPA specification requires a no-args constructor
    public ChatLog() {}

    public ChatLog(Long chatLogId, List<User> users, List<Message> messages) {
        this.chatLogId = chatLogId;
        this.users = users;
        this.messages = messages;
    }

    public Long getChatLogId() {
        return chatLogId;
    }

    public void setChatLogId(Long chatLogId) {
        this.chatLogId = chatLogId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ChatLog{" +
                "chatLogId=" + chatLogId +
                ", users=" + users +
                ", messages=" + messages +
                '}';
    }
}
