package com.dreamers.moves.ts;

import com.dreamers.core.Card;
import com.dreamers.core.GameTable;
import com.dreamers.core.MovePerformDataSource;
import com.dreamers.core.Player;
import com.dreamers.moves.Move;

import java.util.List;

public class DoubleShuffleMove extends Move {

    public void perform(GameTable gameTable, Player player, MovePerformDataSource TSMovePerformDataSource) {

        List<Card> playersHand = player.getPlayersHand();
        List<Card> deckOnTable = gameTable.getDeckOnTable();

        List<Card> cards = TSMovePerformDataSource.getPerformCards();

        deckOnTable.set(deckOnTable.indexOf(cards.get(3)),
                cards.get(1));
        playersHand.set(playersHand.indexOf(cards.get(1)),
                cards.get(3));
        deckOnTable.set(deckOnTable.indexOf(cards.get(4)),
                cards.get(2));
        playersHand.set(playersHand.indexOf(cards.get(2)),
                cards.get(4));

        setPerformedCards(gameTable,player,deckOnTable,playersHand);

    }

}
