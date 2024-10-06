package org.example.risabackend.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MESSAGES")
@Getter @Setter
@NoArgsConstructor // JPA specification requires a no-args constructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    private Long senderId;
    private Long deliveredAt;
    private Long readAt;

    public Message(Long senderId, String message, Long deliveredAt) {
        this.senderId = senderId;
        this.message = message;
        this.deliveredAt = deliveredAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + id +
                ", message='" + message + '\'' +
                ", senderId=" + senderId +
                ", deliveredAt=" + deliveredAt +
                ", readAt=" + readAt +
                '}';
    }
}
