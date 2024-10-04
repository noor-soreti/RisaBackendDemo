package org.example.risabackend.api.controllers;

import org.example.risabackend.api.dto.ChatLogRequestDto;
import org.example.risabackend.services.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "**") // CrossOrigin -> Enables CORS only for specific methods
@RequestMapping("/chatlog")
public class ChatLogController {

    private final ChatLogService chatLogService;

    @Autowired
    public ChatLogController(ChatLogService chatLogService) {
        this.chatLogService = chatLogService;
    }

    // GET

    @GetMapping
    public List<ChatLogRequestDto> getChatLog() {
        return chatLogService.getAllChatLogs();
    }

//
//    @GetMapping("/test/{id}/{uid}")
//    public void test(@PathVariable Long id, @PathVariable Long uid) {
//        Optional<User> user = userRepository.findById(uid);
//        Optional<ChatLog> chatLog =  chatLogRepository.findById(id);
//
//        chatLog.ifPresent(chatLog1 -> {
//            chatLog1.addUser(user.get());
//        });
//
//        chatLogRepository.save(chatLog.get());
//
//        System.out.println("USER: " + user + "\n" + "CHAT LOG: " + chatLog);
//    }

    // POST

//    @PostMapping
//    ChatLog createNewChatLog(@RequestBody ChatLog chatLog) {
//        return chatLogRepository.save(chatLog);
//        ChatLog chatLog = new ChatLog(users, new HashSet<>());
//        return chatLogRepository.save(chatLog);
//    }

    // PUT

    // DELETE

    @DeleteMapping
    public void deleteAllChatLogs() {
        chatLogService.deleteAllChatLogs();
    }

}
