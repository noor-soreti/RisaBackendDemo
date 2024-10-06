package org.example.risabackend.persistence.repositories;

import org.example.risabackend.persistence.entity.ChatLog;
import org.example.risabackend.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
    ChatLog findChatLogByChatLogId(Long id);
    ChatLog findChatLogByUser(User user);
}
