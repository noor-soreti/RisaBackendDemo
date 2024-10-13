package org.example.risabackend.message;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "**")
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    // GET

    @GetMapping("/chatlog/{chatlogid}/messages")
    public List<Message> getMessagesByChatLog(@PathVariable Long chatlogid) {
        return messageService.getAllMessagesByChatLogId(chatlogid);
    }

    // POST
    @PostMapping("/chatlog/{chatlogid}/message")
    public Message addMessage(@PathVariable Long chatlogid, @RequestBody Message message) {
        return messageService.createMessage(chatlogid, message);
    }
}
