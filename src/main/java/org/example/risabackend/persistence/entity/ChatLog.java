package org.example.risabackend.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "CHAT_LOG")
@Getter @Setter
@NoArgsConstructor // JPA specification requires a no-args constructor
public class ChatLog {
    @Id
    @GeneratedValue
    private Long chatLogId;
    @ManyToMany(mappedBy = "chatLogs", fetch = FetchType.EAGER)
    private Set<User> users;
    // One ChatLog can have many Messages
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Message> messages;

    public ChatLog(Set<User> users, Set<Message> messages) {
        this.users = users;
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
