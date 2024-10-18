package org.example.risabackend.exceptions;

import org.example.risabackend.chatlog.ChatLog;

public class ChatLogExistsException extends RuntimeException {
    public ChatLogExistsException(String message) {
        super(message);
    }
}
