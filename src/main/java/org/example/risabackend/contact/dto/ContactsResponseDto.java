package org.example.risabackend.contact.dto;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record ContactsResponseDto(
    Long id,
    Long user_id,
    Long contact_id,
    Timestamp addedAt,
    String name
) { }
