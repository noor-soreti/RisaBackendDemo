package org.example.risabackend.chatlog;

import lombok.extern.slf4j.Slf4j;
import org.example.risabackend.chatlog.dto.ChatLogResponseDto;
import org.example.risabackend.exceptions.ChatLogExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@CrossOrigin(origins = "**") // CrossOrigin -> Enables CORS only for specific methods
@RequestMapping("/api/chatlog")
public class ChatLogController {

    private final ChatLogService chatLogService;

    @Autowired
    public ChatLogController(ChatLogService chatLogService) {
        this.chatLogService = chatLogService;
    }

    // GET
    @GetMapping
    public List<ChatLogResponseDto> getChatLog() {
        return chatLogService.getAllChatLogs();
    }

    @GetMapping("/chatlogid/{chatlogid}")
    public ResponseEntity<ChatLogResponseDto> getChatLogById(@PathVariable Long chatlogid) {
        return ResponseEntity.status(HttpStatus.OK).body(chatLogService.getChatLogById(chatlogid));
    }

    @GetMapping("/userid/{userId}")
    public ResponseEntity<Set<ChatLogResponseDto>> getUserChatLogs(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(chatLogService.getUserChatLogs(userId));
    }

    // POST
    @PostMapping
    public ResponseEntity<ChatLog> createChatLog(@RequestBody Set<Long> userIds) {
       ChatLog chatLog = chatLogService.createChatLog(userIds);
       return ResponseEntity.status(HttpStatus.CREATED).body(chatLog);
    }

    // PUT
    @PutMapping("/appendUserToChatLog/{chatlogid}")
    public ResponseEntity appendUserToChatLog(@PathVariable Long chatlogid, @RequestBody List<Long> userids) {
        chatLogService.appendUserToChatLog(chatlogid, userids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteChatLog(@PathVariable Long id) {
        chatLogService.deleteChatLog(id);
    }

    @DeleteMapping("/multiple")
    public void deleteMultipleChatLogs(@RequestBody Set<Long> userIds) {
        chatLogService.deleteMultipleChatLogs(userIds);
    }

    @DeleteMapping("/all")
    public void deleteAllChatLogs() {
        chatLogService.deleteAllChatLogs();
    }

    // EXCEPTION HANDLING
    @ExceptionHandler(ChatLogExistsException.class)
    public ResponseEntity<String> handleChatLogExistsException(ChatLogExistsException cx) {
        System.out.println(ResponseEntity.status(HttpStatus.CONFLICT).body(cx.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(cx.getMessage());
    }
}