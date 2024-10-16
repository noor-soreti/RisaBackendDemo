package org.example.risabackend.message;

import org.example.risabackend.chatlog.ChatLog;
import org.example.risabackend.chatlog.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatLogRepository chatLogRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatLogRepository chatLogRepository) {
        this.messageRepository = messageRepository;
        this.chatLogRepository = chatLogRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getAllMessagesByChatLogId(Long chatLogId) {
        ChatLog chatLog = chatLogRepository.findById(chatLogId)
                .orElseThrow(() -> new RuntimeException("Chat log not found with id: " + chatLogId));
        List<Message> messages = new ArrayList<>();
        messages.addAll(chatLog.getMessages());
        return messages;
    }

    public Message createMessage(Long chatlogid, Message message) {
        message.setDeliveredAt(new Timestamp(System.currentTimeMillis()));
        Message message1 = chatLogRepository.findById(chatlogid).map(chat -> {
            chat.getMessages().add(message);

            // set recent message to current message
            chat.setRecentMessage(message.getMessage());

            return messageRepository.save(message);
        }).orElseThrow(() -> new RuntimeException("YOIKNS"));
        return message1;
    }

    public Set<Message> findUserMessagesInChatLog (Long chatlogid, Long userid) {
        ChatLog chatLog = chatLogRepository.findChatLogById(chatlogid);

        if (chatLog == null) {
            throw new RuntimeException("Chat log not found with id: " + chatlogid);
        }

        Set<Message> messageSet = chatLog.getMessages();
        Set<Message> returnMessages = new HashSet<>();
        messageSet.forEach(message -> {
            if (message.getSenderId().equals(userid)) {
                returnMessages.add(message);
            }
        });
        System.out.println(returnMessages);
        return returnMessages;
    }

    public void deleteAllMessages () {
        messageRepository.deleteAll();
    }
}