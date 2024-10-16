package org.example.risabackend.contact;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CONTACTS")
@Getter @Setter
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long contactId;

    private Long addedAt;
    private String displayName;

    public Contact(Long userId, Long contactId, Long addedAt, String displayName) {
        this.userId = userId;
        this.contactId = contactId;
        this.addedAt = addedAt;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", ownerId=" + userId +
                ", contactUserId=" + contactId +
                ", displayName='" + displayName + '\'' +
                ", addedAt=" + addedAt +
                '}';
    }
}
