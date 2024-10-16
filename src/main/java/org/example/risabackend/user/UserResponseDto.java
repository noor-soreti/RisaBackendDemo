package org.example.risabackend.user;

import org.example.risabackend.chatlog.ChatLog;

import java.sql.Timestamp;
import java.util.Set;
import lombok.Builder;
import org.example.risabackend.contact.Contact;

@Builder
public record UserResponseDto(
        Long id,
        String fullName,
        String phoneNumber,
        String avatar,
        String status,
        Timestamp lastSeen,
        boolean isOnline,
        Set<ChatLog> chatLogs,
        Set<Contact> contacts) {
}
