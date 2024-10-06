/*
* ChatLog is owning side because users can join/leave a chatroom
* owning side specified with @JoinTable annotation
* since the relationship is BI-DIRECTIONAL, the inverse side needs mappedBy to specify relationship field
* */

package org.example.risabackend.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CHAT_LOG")
@Getter @Setter
@NoArgsConstructor // JPA specification requires a no-args constructor
public class ChatLog {
    @Id
    @GeneratedValue
    @Column(name = "chat_log_id")
    private Long chatLogId;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "chatlog_users",
            joinColumns = { @JoinColumn(name = "chatlog_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users;

    // One ChatLog can have many Messages
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Message> messages;

    public ChatLog(Set<User> users) {
        this.users = users;
        this.messages = new HashSet<>();
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