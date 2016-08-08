package com.dreamers.controller;

import com.dreamers.GameCore.Player;
import com.dreamers.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Player>> getAll() {
        List<Player> players = playerService.getAll();
        return new ResponseEntity<>(players,HttpStatus.OK);
    }

    @RequestMapping(value = "/getstring", method = RequestMethod.GET)
    public ResponseEntity<String> getString () {
        return new ResponseEntity<>("This shit rly works, YEAH!",HttpStatus.OK);}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Player> get(@PathVariable("id") int id) {

        return new ResponseEntity<>(playerService.get(id),HttpStatus.OK);
    }

}
