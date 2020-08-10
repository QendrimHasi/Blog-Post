package com.blog.ui.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.blog.ui.model.request.ChatRequestModel;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ChatController {
	
	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatRequestModel register(@Payload ChatRequestModel chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatRequestModel sendMessage(@Payload ChatRequestModel chatMessage) {
		return chatMessage;
	}

}
