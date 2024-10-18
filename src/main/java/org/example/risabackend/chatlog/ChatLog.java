/*
* ChatLog is owning side because users can join/leave a chatroom
* owning side specified with @JoinTable annotation
* since the relationship is BI-DIRECTIONAL, the inverse side needs mappedBy to specify relationship field
* */

package org.example.risabackend.chatlog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.risabackend.message.Message;
import org.example.risabackend.user.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "CHATLOGS")
@Getter @Setter
@NoArgsConstructor // JPA specification requires a no-args constructor
public class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String recentMessage;

    // One ChatLog can have many Messages
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chatlog_id")
    private Set<Message> messages;

    @ManyToMany(cascade = {
                CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "chatlog_users",
            joinColumns = { @JoinColumn(name = "chatlog_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users;

    public ChatLog(Set<User> users) {
        this.users = users;
        this.messages = new HashSet<>();
    }

    public Set<Long> getUserIdsFromChatLog(ChatLog chatLog) {
        return chatLog.getUsers()
                .stream().map(User::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "ChatLog{" +
                "chatLogId=" + id +
                ", users=" + users +
                ", messages=" + messages +
                '}';
    }
}