package org.example.risabackend.api.dto;

import org.example.risabackend.persistence.entity.ChatLog;

import java.util.Set;
import lombok.Builder;

@Builder
public record UserResponseDto(
        Long id,
        String email,
        String fullName,
        String profilePicture,
        String status,
        Long lastSeen,
        boolean isOnline,
        Set<ChatLog> chatLogs) {
}
