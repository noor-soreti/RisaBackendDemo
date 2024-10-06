package org.example.risabackend.services;

import org.example.risabackend.api.dto.ChatLogRequestDto;
import org.example.risabackend.api.dto.UserResponseDto;
import org.example.risabackend.persistence.entity.ChatLog;
import org.example.risabackend.persistence.entity.Message;
import org.example.risabackend.persistence.entity.User;
import org.example.risabackend.persistence.repositories.ChatLogRepository;
import org.example.risabackend.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                            .chatLogId(chatLog.getChatLogId())
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
                .chatLogId(chatLog.getChatLogId())
                .message(chatLog.getMessages())
                .users(chatLog.getUsers())
                .build();
    }

    public String getUserChatLog(Long userId) {
        User user = userRepository.findFirstByUserId(userId);
        if (user == null) {
            return "BYE";
        }
        UserResponseDto userResponseDto = UserResponseDto.builder()
                                                        .id(user.getUserId())
                                                        .email(user.getEmail())
                                                        .fullName(user.getFullName())
                                                        .profilePicture(user.getProfilePicture())
                                                        .status(user.getStatus())
                                                        .lastSeen(user.getLastSeen())
                                                        .isOnline(user.isOnline())
                                                        .chatLogs(user.getChatLogs())
                                                        .build();
        System.out.println(userResponseDto);
        return "HELLO";
    }

    public Long createChatLog(Set<Long> userIds) {
        System.out.println("WHAT THE HELL");
        List<User> users = userRepository.findAllById(userIds);
        Set<User> userSet = new HashSet<>(users);

        ChatLog chatLog = new ChatLog(userSet);
        ChatLog clog =  chatLogRepository.saveAndFlush(chatLog);
        ChatLogRequestDto.builder()
                .chatLogId(clog.getChatLogId())
                .message(clog.getMessages())
                .users(clog.getUsers())
                .build();
        return clog.getChatLogId();
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
                .chatLogId(chatLog.getChatLogId())
                .message(chatLog.getMessages())
                .users(chatLog.getUsers())
                .build();

    }

    public void deleteAllChatLogs() {
        chatLogRepository.deleteAll();
    }

}
