package org.example.risabackend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.risabackend.chatlog.ChatLog;
import org.example.risabackend.contact.Contact;

import java.sql.Timestamp;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String password;

    private String phoneNumber;

    private String avatar = "profilePicture";

    private String status;

    private Timestamp lastSeen;

    private boolean isOnline;

    // A User can "belong" to many ChatLogs
    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<ChatLog> chatLogs;

    // One User can have many Contacts
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Contact> contacts = new HashSet<>();

    public User(String fullName, String phoneNumber, String password) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.status = "default";
        this.isOnline = true;
        this.chatLogs = new HashSet<>();
//        this.contacts = new HashSet<>();
    }

    public User(String fullName, String phoneNumber, String password, Timestamp lastSeen) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.lastSeen = lastSeen;
        this.status = "default";
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
                ", profilePicture = '" + avatar + '\'' +
                ", status = '" + status + '\'' +
                ", lastSeen = " + lastSeen +
                ", isOnline = " + isOnline +
                ", chatLogs = " + chatLogs +
                '}';
    }
}
