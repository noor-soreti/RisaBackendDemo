package org.example.risabackend.message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @GetMapping("/getUserMessagesFromChatlog/{chatlogid}/{userid}")
    public Set<Message> getUserMessagesFromChatlog(@PathVariable Long chatlogid, @PathVariable Long userid) {
        return messageService.findUserMessagesInChatLog(chatlogid, userid);
    }

    // POST
    @PostMapping("/sendMessage/{chatlogid}")
    public Message sendMessage(@PathVariable Long chatlogid, @RequestBody Message message) throws Exception {
//        Thread.sleep(1000);
        return messageService.createMessage(chatlogid, message);
    }

    // DELETE
    @DeleteMapping
    public void deleteMessage() {
        messageService.deleteAllMessages();
    }
}
