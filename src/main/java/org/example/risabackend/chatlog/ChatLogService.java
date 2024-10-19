package org.example.risabackend.chatlog;

import lombok.extern.slf4j.Slf4j;
import org.example.risabackend.chatlog.dto.ChatLogResponseDto;
import org.example.risabackend.exceptions.ChatLogExistsException;
import org.example.risabackend.user.dto.UserResponseDto;
import org.example.risabackend.message.Message;
import org.example.risabackend.user.User;
import org.example.risabackend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
public class ChatLogService {
    private final ChatLogRepository chatLogRepository;
    private final UserRepository userRepository;

    public ChatLogService(ChatLogRepository chatLogRepository, UserRepository userRepository) {
        this.chatLogRepository = chatLogRepository;
        this.userRepository = userRepository;
    }

    public List<ChatLogResponseDto> getAllChatLogs() {
        List<ChatLog> chatLogs = chatLogRepository.findAll();
        List<ChatLogResponseDto> chatLogRequestDtos = new ArrayList<>();
        for (ChatLog chatLog : chatLogs) {
            Set<String> names = new HashSet<>();

            for (User user : chatLog.getUsers()) {
                names.add(user.getFullName());
            }

            chatLogRequestDtos.add(
                    ChatLogResponseDto.builder()
                    .chatLogId(chatLog.getId())
//                    .message(chatLog.getMessages())
                    .names(names)
                    .build()
            );
        }
        return chatLogRequestDtos;
    }

    public ChatLogResponseDto getChatLogById(long chatLogId) {
        ChatLog chatLog = chatLogRepository.findById(chatLogId).orElse(null);
        if (chatLog == null) {
            throw new RuntimeException("Chat log with id " + chatLogId + " not found");
        }

        Set<String> names = new HashSet<>();

        for (User user : chatLog.getUsers()) {
            names.add(user.getFullName());
        }

        return ChatLogResponseDto.builder()
                .chatLogId(chatLog.getId())
//                .message(chatLog.getMessages())
                .names(names)
                .build();
    }

    public Set<ChatLogResponseDto> getUserChatLogs(Long userId) {
        User userEntity = userRepository.findFirstById(userId);
        Set<ChatLog> chatLogSet = userEntity.getChatLogs();
        Set<ChatLogResponseDto> chatLogResponseDtoSet = new HashSet<>();

        for (ChatLog chatLog : chatLogSet) {
            // get all names except currentUser
            Set<String> names = new HashSet<>();
            for (User u: chatLog.getUsers()) {
                if (!u.getFullName().equals(userEntity.getFullName())) {
                    names.add(u.getFullName());
                }
            }
            chatLogResponseDtoSet.add(
                    ChatLogResponseDto.builder()
                    .chatLogId(chatLog.getId())
                    .recentMessage(chatLog.getRecentMessage())
                    .names(names).build()
            );
        }

        return chatLogResponseDtoSet;
    }

    public ChatLog createChatLog(Set<Long> userIds) {
        List<User> users = userRepository.findAllById(userIds);
        ChatLog chatLog = new ChatLog(new HashSet<>(users));

        Set<Long> chatLogUserIds = new HashSet<>();

        // get any user
        User firstUser = users.get(0);
        // iterate through user's chatLog
        for (ChatLog logs: firstUser.getChatLogs()) {
            // extract userIds
            logs.getUsers().forEach(chatUser -> {
                // populate set with ids of users present in chatLog
                chatLogUserIds.add(chatUser.getId());
            });
        }

        if (chatLogUserIds.equals(userIds)) {
            throw new ChatLogExistsException("ChatLog with users: " + chatLogUserIds + " already exists");
        }

        return chatLogRepository.save(chatLog);

    }

    public void appendUserToChatLog(Long chatLogId, List<Long> userId) {
        List<User> userList = userRepository.findAllById(userId);
        ChatLog chatLog = chatLogRepository.findById(chatLogId).orElse(null);
        if (chatLog == null) {
            return;
        }
        chatLog.getUsers().addAll(userList);
        chatLogRepository.saveAndFlush(chatLog);
    }

    public void deleteChatLog(Long chatLogId) {
        chatLogRepository.deleteById(chatLogId);
    }

    public void deleteMultipleChatLogs(Set<Long> chatLogIds) {
        chatLogRepository.deleteAllById(chatLogIds);
    }

    public void deleteAllChatLogs() {
        chatLogRepository.deleteAll();
    }

}
