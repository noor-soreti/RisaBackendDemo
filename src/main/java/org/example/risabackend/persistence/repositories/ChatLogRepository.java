package org.example.risabackend.persistence.repositories;

import org.example.risabackend.persistence.entity.ChatLog;
import org.example.risabackend.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
    Optional<ChatLog> findChatLogByChatLogId(Long id);
    Optional<ChatLog> findByUsers(Set<User> users);
}
