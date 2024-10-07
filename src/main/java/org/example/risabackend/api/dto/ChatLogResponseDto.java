package org.example.risabackend.api.dto;

import lombok.Builder;
import org.example.risabackend.persistence.entity.Message;
import org.example.risabackend.persistence.entity.User;

import java.util.Set;

@Builder
public record ChatLogResponseDto(
        Long chatLogId,
        Set<Message> message,
        Set<User> users
) {
}
