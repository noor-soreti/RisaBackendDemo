package org.example.risabackend.contact;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "CONTACTS")
@Getter @Setter
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long user_id;

    private Long contact_id;

    private Timestamp addedAt;
    private String displayName;

    public Contact(Long user_id, Long contact_id, Timestamp addedAt, String displayName) {
        this.user_id = user_id;
        this.contact_id = contact_id;
        this.addedAt = addedAt;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id + '\'' +
                ", ownerId=" + user_id + '\'' +
                ", contactUserId=" + contact_id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", addedAt=" + addedAt +
                '}';
    }
}
