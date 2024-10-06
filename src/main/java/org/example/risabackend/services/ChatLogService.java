package org.example.risabackend.services;

import org.example.risabackend.api.dto.ChatLogRequestDto;
import org.example.risabackend.api.dto.UserResponseDto;
import org.example.risabackend.persistence.entity.ChatLog;
import org.example.risabackend.persistence.entity.Message;
import org.example.risabackend.persistence.entity.User;
import org.example.risabackend.persistence.repositories.ChatLogRepository;
import org.example.risabackend.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatLogService {
    private final ChatLogRepository chatLogRepository;
    private final UserRepository userRepository;

    public ChatLogService(ChatLogRepository chatLogRepository, UserRepository userRepository) {
        this.chatLogRepository = chatLogRepository;
        this.userRepository = userRepository;
    }

    public List<ChatLogRequestDto> getAllChatLogs() {
        List<ChatLog> chatLogs = chatLogRepository.findAll();
        List<ChatLogRequestDto> chatLogRequestDtos = new ArrayList<>();
        for (ChatLog chatLog : chatLogs) {
            chatLogRequestDtos.add(
                    ChatLogRequestDto.builder()
                            .chatLogId(chatLog.getId())
                            .message(chatLog.getMessages())
                            .users(chatLog.getUsers())
                            .build()
            );
        }
        return chatLogRequestDtos;
    }

    public ChatLogRequestDto getChatLogById(long chatLogId) {
        ChatLog chatLog = chatLogRepository.findById(chatLogId).orElse(null);
        if (chatLog == null) {
            return null;
        }
        return ChatLogRequestDto.builder()
                .chatLogId(chatLog.getId())
                .message(chatLog.getMessages())
                .users(chatLog.getUsers())
                .build();
    }

    public Optional<Set<ChatLog>> getUserChatLog(Long userId) {
        User userEntity = userRepository.findFirstByUserId(userId);
        UserResponseDto userResponseDto = UserResponseDto.builder()
                                                        .id(userEntity.getId())
                                                        .email(userEntity.getEmail())
                                                        .fullName(userEntity.getFullName())
                                                        .profilePicture(userEntity.getProfilePicture())
                                                        .status(userEntity.getStatus())
                                                        .lastSeen(userEntity.getLastSeen())
                                                        .isOnline(userEntity.isOnline())
                                                        .chatLogs(userEntity.getChatLogs())
                                                        .build();
        return Optional.of(userResponseDto.chatLogs());

    }

    public void createChatLog(Set<Long> userIds) {
        List<User> users = userRepository.findAllById(userIds);
        Set<User> userSet = new HashSet<>(users);

        ChatLog chatLog = new ChatLog(userSet);
        ChatLog clog =  chatLogRepository.save(chatLog);
        System.out.println(clog);
//        System.out.println(clog.getChatLogId());
//        ChatLogRequestDto.builder()
//                .chatLogId(clog.getChatLogId())
//                .message(clog.getMessages())
//                .users(clog.getUsers())
//                .build();
//        return clog.getChatLogId();
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

    public ChatLogRequestDto appendMessageToChatLog(Long chatLogId, Long userId, String message) {
        Message message1 = new Message(userId, message, System.currentTimeMillis());
        System.out.println(message1.toString());

        ChatLog chatLog = chatLogRepository.findById(chatLogId).orElse(null);
        chatLog.getMessages().add(message1);
        chatLogRepository.saveAndFlush(chatLog);
        return ChatLogRequestDto.builder()
                .chatLogId(chatLog.getId())
                .message(chatLog.getMessages())
                .users(chatLog.getUsers())
                .build();

    }

    public void deleteAllChatLogs() {
        chatLogRepository.deleteAll();
    }

}
