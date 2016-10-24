package com.dreamers.moves.ts;

import com.dreamers.core.Card;
import com.dreamers.core.GameTable;
import com.dreamers.core.MovePerformDataSource;
import com.dreamers.core.Player;
import com.dreamers.moves.Move;

import java.util.List;


public class SingleShuffleMove extends Move {

    public void perform(GameTable gameTable, Player player, MovePerformDataSource movePerformDataSource) {

        List<Card> playersHand = player.getPlayersHand();
        List<Card> deckOnTable = gameTable.getDeckOnTable();

        //getting cards from DataSource
        List<Card> cards = movePerformDataSource.getPerformCards();

        //switching cards, based on information how cards were put in array
        deckOnTable.set(deckOnTable.indexOf(cards.get(1)),
                cards.get(0));
        playersHand.set(playersHand.indexOf(cards.get(0)),
                cards.get(1));

        setPerformedCards(gameTable,player,deckOnTable,playersHand);
    }
}
