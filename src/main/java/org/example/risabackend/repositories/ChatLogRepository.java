package org.example.risabackend.repositories;

import org.example.risabackend.models.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
//    ChatLog findByUserId(String userId);
}
