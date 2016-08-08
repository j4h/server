package com.dreamers.Moves;

import com.dreamers.GameCore.Card;
import com.dreamers.GameCore.GameTable;
import com.dreamers.GameCore.MovePerformDataSource;
import com.dreamers.GameCore.Player;

import java.util.List;

public abstract class Move {

    public abstract void perform(GameTable gameTable, Player activePlayer, MovePerformDataSource movePerformDataSource);

    protected void setPerformedCards(GameTable gameTable, Player player, List<Card> deckOnTable,
                                  List<Card> playersHand) {
        gameTable.setDeckOnTable(deckOnTable);
        player.setPlayersHand(playersHand);
    }
}