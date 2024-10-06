package org.example.risabackend.api.controllers;

import org.example.risabackend.api.dto.ChatLogRequestDto;
import org.example.risabackend.api.dto.UserResponseDto;
import org.example.risabackend.persistence.entity.ChatLog;
import org.example.risabackend.persistence.entity.User;
import org.example.risabackend.services.ChatLogService;
import org.example.risabackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/chatlogid/{chatlogid}")
    public ChatLogRequestDto getChatLogById(@PathVariable Long chatlogid) {
        return chatLogService.getChatLogById(chatlogid);
    }

    @GetMapping("/userid/{userId}")
    public String getUserChatLog(@PathVariable Long userId) {
        return chatLogService.getUserChatLog(userId);
    }

    // POST
    @PostMapping
    public Long createChatLog(@RequestBody Set<Long> userId) {
       return chatLogService.createChatLog(userId);
    }

    @PostMapping("/appendUserToChatLog/{chatlogid}")
    public void appendUserToChatLog(@PathVariable Long chatlogid, @RequestBody List<Long> userids) {
        chatLogService.appendUserToChatLog(chatlogid, userids);
    }

    // PUT

    // DELETE

    @DeleteMapping
    public void deleteAllChatLogs() {
        chatLogService.deleteAllChatLogs();
    }

}
