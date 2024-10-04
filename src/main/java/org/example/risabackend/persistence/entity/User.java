package org.example.risabackend.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor // JPA specification requires a no-args constructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED) // change visibility of id setter property
    private Long userId;
    private String fullName;
    private String password;
    private String email;
    private String phoneNumber = "123-123-1234";
    private String profilePicture = "profilePicture";
    private String status;
    private Long lastSeen;
    private boolean isOnline;
    @ManyToMany(fetch = FetchType.EAGER) // collections are lazy-loaded by default, need to specify fetch
    private Set<ChatLog> chatLogs;


    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.status = "default";
        this.lastSeen = System.currentTimeMillis();
        this.isOnline = true;
        this.chatLogs = new HashSet<>();
    }

    public void encrypt(String password) {
        this.password = password;
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
                '}';
    }
}
