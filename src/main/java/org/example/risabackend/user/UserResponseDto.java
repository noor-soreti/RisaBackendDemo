package org.example.risabackend.user;

import org.example.risabackend.chatlog.ChatLog;

import java.util.Set;
import lombok.Builder;

@Builder
public record UserResponseDto(
        Long id,
        String fullName,
        String phoneNumber,
        String profilePicture,
        String status,
        Long lastSeen,
        boolean isOnline,
        Set<ChatLog> chatLogs) {
}
