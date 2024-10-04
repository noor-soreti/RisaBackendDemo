package org.example.risabackend.services;

import org.example.risabackend.api.dto.ChatLogRequestDto;
import org.example.risabackend.persistence.entity.ChatLog;
import org.example.risabackend.persistence.repositories.ChatLogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatLogService {
    private ChatLogRepository chatLogRepository;

    public ChatLogService(ChatLogRepository chatLogRepository) {
        this.chatLogRepository = chatLogRepository;
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

    public void deleteAllChatLogs() {
        chatLogRepository.deleteAll();
    }

}
