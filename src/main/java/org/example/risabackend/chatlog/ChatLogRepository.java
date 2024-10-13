package org.example.risabackend.chatlog;

import org.example.risabackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
    ChatLog findChatLogById(Long id);
    ChatLog findChatLogByUsers(Set<User> users);
}
