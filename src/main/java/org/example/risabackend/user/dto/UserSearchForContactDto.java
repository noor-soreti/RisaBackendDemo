package org.example.risabackend.user.dto;

import lombok.Builder;

@Builder
public record UserSearchForContactDto (
        Long id,
        String fullName,
        String phoneNumber,
        String avatar,
        boolean isOnline
){ }
