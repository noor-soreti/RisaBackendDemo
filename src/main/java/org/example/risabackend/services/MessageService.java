package org.example.risabackend.services;

import org.example.risabackend.persistence.entity.ChatLog;
import org.example.risabackend.persistence.entity.Message;
import org.example.risabackend.persistence.repositories.ChatLogRepository;
import org.example.risabackend.persistence.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatLogRepository chatLogRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatLogRepository chatLogRepository) {
        this.messageRepository = messageRepository;
        this.chatLogRepository = chatLogRepository;
    }

    public List<Message> getAllMessagesByChatLogId(Long chatLogId) {
        ChatLog chatLog = chatLogRepository.findById(chatLogId)
                .orElseThrow(() -> new RuntimeException("Chat log not found with id: " + chatLogId));
        List<Message> messages = new ArrayList<>();
        messages.addAll(chatLog.getMessages());
        return messages;
    }

    public Message createMessage(Long chatlogid, Message message) {
        Message message1 = chatLogRepository.findById(chatlogid).map(chat -> {
            chat.getMessages().add(message);
            return messageRepository.save(message);
        }).orElseThrow(() -> new RuntimeException("YOIKNS"));
        return message;
    }

}
