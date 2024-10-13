package org.example.risabackend.chatlog;

import lombok.Builder;
import org.example.risabackend.message.Message;
import org.example.risabackend.user.User;

import java.util.Set;

@Builder
public record ChatLogResponseDto(
        Long chatLogId,
        Set<Message> message,
        Set<User> users
) {
}
