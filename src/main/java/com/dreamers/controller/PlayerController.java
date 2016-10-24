package com.dreamers.controller;

import com.dreamers.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerController {

    private PlayerService playerService;
    private SimpMessagingTemplate template;

    @Autowired
    public PlayerController (PlayerService playerService, SimpMessagingTemplate template) {
        this.template = template;
        this.playerService = playerService;
    }

    @MessageMapping("/players")
    public void getAll() {
        template.convertAndSend("/topic/players", playerService.getAll());
    }

    @MessageMapping("/player")
    public void get(int get) throws Exception {
        template.convertAndSend("/topic/players", playerService.get(get));
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
