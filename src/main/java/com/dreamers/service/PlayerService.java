package com.dreamers.service;

import com.dreamers.GameCore.Card;
import com.dreamers.GameCore.GameTable;
import com.dreamers.GameCore.Player;
import com.dreamers.TSGamePackage.TSPlayer;
import com.dreamers.exception.NotFound;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private static Log logger = LogFactory.getLog(PlayerService.class);
    private final List<Player> players = createFakePlayers1();

    public List<Player> getAll(){return players;}

    public Player get(int index) {
        Player player = players.get(index);
        if (player == null){
            throw new NotFound(index);
        } else return player;
    }

    private List<Player> createFakePlayers1() {

        List<Player> players = new ArrayList<>();
        GameTable gameTable = new GameTable();
        List<Card> cards = gameTable.fill36CardsDeck();
        Player player = new TSPlayer("Huek");
        Player player1 = new TSPlayer("Huek1");
        player.setPlayersHand(cards);
        player1.setPlayersHand(cards);
        players.add(player);
        players.add(player1);
        return players;
    }
}
