package com.dreamers.controller;

import com.dreamers.GameCore.Player;
import com.dreamers.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;

import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    public List<Player> getAll() throws Exception {
        Thread.sleep(1000);
        System.out.println("getAll() triggered!");
        template.convertAndSend("/topic/players", playerService.getAll());
        return playerService.getAll();
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
