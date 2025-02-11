package com.example.MessegingApp.Controllers;

import com.example.MessegingApp.Entity.ChatMessage;
import com.example.MessegingApp.Entity.PrivateChatMessage;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/sendMessage") // Handles messages sent to /app/sendMessage
    @SendTo("/topic/messages") // Broadcast messages to /topic/messages
    public ChatMessage sendMessage(ChatMessage message) {
        return message; // Simply return the message to be broadcast
    }

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @SendToUser
    @MessageMapping("/private-message") // Handles messages sent to /app/private-message
    public void sendPrivateMessage(@Payload PrivateChatMessage message) {
        // Send message to the recipient's private queue
        System.out.println(message.getRecipient() +" "+message.getSender()+" "+message.getContent());
        messagingTemplate.convertAndSendToUser(
                message.getRecipient(), "/queue/private", message
        );
    }
}

