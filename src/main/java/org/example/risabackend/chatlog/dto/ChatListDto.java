package org.example.risabackend.chatlog.dto;

import lombok.Builder;

@Builder
public record ChatListDto(
        Long id,
        String name,
        String lastMessage
) { }
