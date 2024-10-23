package org.example.risabackend.config;

import org.example.risabackend.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chatlog/{roomId}")
//    @SendTo("/topic/chat")
    public void sendMessageToRoom(@DestinationVariable String roomId, Message message) {
        message.setDeliveredAt(new Timestamp(System.currentTimeMillis()));
        System.out.println("Message received in room: " + roomId);
        messagingTemplate.convertAndSend("/topic/chat-" + roomId, message);
//        return new Message(message.getSenderId(), message.getMessage());
    }
}

/*
* MAJOR CHANGES:
* @SendTo("") annotation does not allow dynamic placeholders (for routing to spec. topic)
* SimpMessagingTemplate -> use to dynamically specify destination
* changed @MessageMapping("/chatlog") to @MessageMapping("/chatlog/{roomId}")
*       -> to listen for messages sent to "/app/chatlog/{roomId}" from the client and forwards them to "/topic/chat-{roomId}" via mess...convertAndSend(...)
* */