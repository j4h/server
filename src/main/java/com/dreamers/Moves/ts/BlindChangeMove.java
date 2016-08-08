package com.dreamers.Moves.ts;

import com.dreamers.GameCore.Card;
import com.dreamers.GameCore.GameTable;
import com.dreamers.GameCore.MovePerformDataSource;
import com.dreamers.GameCore.Player;
import com.dreamers.Moves.Move;

import java.util.ArrayList;
import java.util.List;

public class BlindChangeMove extends Move {

    public void perform(GameTable gameTable, Player player, MovePerformDataSource TSMovePerformDataSource) {

        List<Card> playersHand = player.getPlayersHand();
        List<Card> deckOnTable = gameTable.getDeckOnTable();

        List<Card> container = new ArrayList<>();

        container.addAll(deckOnTable);
        container.addAll(playersHand);
        player.clearCards();
        deckOnTable.clear();

        playersHand.addAll(container.subList(0, 3));
        deckOnTable.addAll(container.subList(3, 6));

        setPerformedCards(gameTable,player,deckOnTable,playersHand);
    }
}
