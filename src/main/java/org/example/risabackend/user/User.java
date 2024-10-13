package org.example.risabackend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.risabackend.chatlog.ChatLog;
import org.example.risabackend.contact.Contact;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor // JPA specification requires a no-args constructor
public class User {
    // @Setter(AccessLevel.PROTECTED) // change visibility of id setter property
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String password;
    private String phoneNumber;
    private String profilePicture = "profilePicture";
    private String status;
    private Long lastSeen;
    private boolean isOnline;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER) // collections are lazy-loaded by default, need to specify fetch
    @JsonIgnore
    private Set<ChatLog> chatLogs;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<Contact> contacts;

    public User(String fullName, String phoneNumber, String password) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.status = "default";
        this.lastSeen = System.currentTimeMillis();
        this.isOnline = true;
        this.chatLogs = new HashSet<>();
        this.contacts = new HashSet<>();
    }

    public void encrypt(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id = " + id +
                ", fullName = '" + fullName + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", profilePicture = '" + profilePicture + '\'' +
                ", status = '" + status + '\'' +
                ", lastSeen = " + lastSeen +
                ", isOnline = " + isOnline +
                ", chatLogs = " + chatLogs +
                '}';
    }
}
