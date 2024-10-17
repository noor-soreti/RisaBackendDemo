package org.example.risabackend.chatlog;

import org.example.risabackend.user.dto.UserResponseDto;
import org.example.risabackend.message.Message;
import org.example.risabackend.user.User;
import org.example.risabackend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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
            chatLogRequestDtos.add(
                    ChatLogResponseDto.builder()
                            .chatLogId(chatLog.getId())
                            .message(chatLog.getMessages())
                            .users(chatLog.getUsers())
                            .build()
            );
        }
        return chatLogRequestDtos;
    }

    public ChatLogResponseDto getChatLogById(long chatLogId) {
        ChatLog chatLog = chatLogRepository.findById(chatLogId).orElse(null);
        if (chatLog == null) {
            return null;
        }
        return ChatLogResponseDto.builder()
                .chatLogId(chatLog.getId())
                .message(chatLog.getMessages())
                .users(chatLog.getUsers())
                .build();
    }

    public Optional<Set<ChatLog>> getUserChatLogs(Long userId) {
        User userEntity = userRepository.findFirstById(userId);
        UserResponseDto userResponseDto = UserResponseDto.builder()
                                                        .id(userEntity.getId())
                                                        .fullName(userEntity.getFullName())
                                                        .avatar(userEntity.getAvatar())
                                                        .status(userEntity.getStatus())
                                                        .lastSeen(userEntity.getLastSeen())
                                                        .isOnline(userEntity.isOnline())
                                                        .chatLogs(userEntity.getChatLogs())
                                                        .build();
        return Optional.of(userResponseDto.chatLogs());

    }

    public ChatLog createChatLog(Set<Long> userIds) {
        List<User> users = userRepository.findAllById(userIds);

        ChatLog chatLog = new ChatLog(new HashSet<>(users));
        return chatLogRepository.save(chatLog);

//        ChatLogResponseDto.builder()
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

    public ChatLogResponseDto appendMessageToChatLog(Long chatLogId, Long userId, String message) {
        Message message1 = new Message(userId, message, new Timestamp(System.currentTimeMillis()));
        System.out.println(message1.toString());

        ChatLog chatLog = chatLogRepository.findById(chatLogId).orElse(null);
        chatLog.getMessages().add(message1);
        chatLogRepository.saveAndFlush(chatLog);
        return ChatLogResponseDto.builder()
                .chatLogId(chatLog.getId())
                .message(chatLog.getMessages())
                .users(chatLog.getUsers())
                .build();

    }

    public void deleteChatLog(Long chatLogId) {
        chatLogRepository.deleteById(chatLogId);
    }

    public void deleteAllChatLogs() {
        chatLogRepository.deleteAll();
    }

}
