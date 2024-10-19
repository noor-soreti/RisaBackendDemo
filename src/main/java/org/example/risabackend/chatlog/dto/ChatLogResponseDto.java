package org.example.risabackend.chatlog.dto;

import lombok.Builder;
import org.example.risabackend.message.Message;
import org.example.risabackend.user.User;

import java.util.Set;

@Builder
public record ChatLogResponseDto(
        Long chatLogId,
        String recentMessage,
//        Set<Message> message,
        Set<String> names
) {
}
