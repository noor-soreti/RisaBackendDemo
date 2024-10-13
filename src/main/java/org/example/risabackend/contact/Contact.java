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

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "contact_user_id", nullable = false)
    private Long contactUserId;

    private String displayName;
    private Long addedAt;

    public Contact(Long userId, Long contactUserId) {
        this.userId = userId;
        this.contactUserId = contactUserId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", ownerId=" + userId +
                ", contactUserId=" + contactUserId +
                ", displayName='" + displayName + '\'' +
                ", addedAt=" + addedAt +
                '}';
    }
}
