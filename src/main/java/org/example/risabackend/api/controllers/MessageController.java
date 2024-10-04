package org.example.risabackend.api.controllers;

import org.example.risabackend.persistence.entity.Message;
import org.example.risabackend.persistence.repositories.MessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "**")
@RequestMapping("/message")
public class MessageController {
    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @PostMapping
    @ResponseBody
    Message addMessage(@RequestBody Message message) {
        System.out.println("hello");
        return messageRepository.save(message);
    }
}
