package com.dreamers.moves;

import com.dreamers.core.Card;
import com.dreamers.core.GameTable;
import com.dreamers.core.MovePerformDataSource;
import com.dreamers.core.Player;

import java.util.List;

public abstract class Move {

    public abstract void perform(GameTable gameTable, Player activePlayer, MovePerformDataSource movePerformDataSource);

    protected void setPerformedCards(GameTable gameTable, Player player, List<Card> deckOnTable,
                                  List<Card> playersHand) {
        gameTable.setDeckOnTable(deckOnTable);
        player.setPlayersHand(playersHand);
    }
}