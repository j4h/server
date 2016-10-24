package com.dreamers.service;

import com.dreamers.core.Card;
import com.dreamers.core.GameTable;
import com.dreamers.core.Player;
import com.dreamers.threeSticks.TSPlayer;
import com.dreamers.exception.NotFound;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final List<Player> players = createFakePlayersWithCards();
    //private static Log logger = LogFactory.getLog(PlayerService.class);

    public List<Player> getAll(){return players;}

    public Player get(int index) throws Exception {
        Player player = players.get(index);
        if (player == null){
            throw new NotFound(index);
        } else return player;
    }

    private List<Player> createFakePlayersWithCards() {
        List<Player> players = new ArrayList<>();
        GameTable gameTable = new GameTable();
        List<Card> cards = gameTable.fill36CardsDeck();
        Player player = new TSPlayer("Player0");
        Player player1 = new TSPlayer("Player1");
        players.add(player);
        players.add(player1);
        players.forEach(player2 -> player2.setPlayersHand(cards));
        return players;
    }
}
