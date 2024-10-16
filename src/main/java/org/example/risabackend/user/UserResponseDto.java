package org.example.risabackend.user;

import org.example.risabackend.chatlog.ChatLog;

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
        Long lastSeen,
        boolean isOnline,
        Set<ChatLog> chatLogs,
        Set<Contact> contacts) {
}
