package com.dreamers.threeSticks;

import com.dreamers.core.Card;
import com.dreamers.core.MovePerformDataSource;

import java.util.ArrayList;
import java.util.List;

class TSMovePerformDataSource extends MovePerformDataSource {

    //card for perform container
    private List<Card> cardsForPerform = new ArrayList<>();

    //saving data for performing SingleShuffle move
    List<Card> createDataForSingleMove(TSGameTable gameTable, TSPlayer player, int playerCardInput, int tableCardInput) {

        List<Card> playersHand = player.getPlayersHand();
        List<Card> deckOnTable = gameTable.getDeckOnTable();
        //clear container before each usage
        cardsForPerform.clear();
        //cleaning array
        cardsForPerform.add(playersHand.get(playerCardInput));
        cardsForPerform.add(deckOnTable.get(tableCardInput));
        setPerformedCards(cardsForPerform);
        return cardsForPerform;
    }

    //same for DoubleShuffle
    List<Card> createDataForDoubleMove(TSGameTable gameTable, TSPlayer player, int input1, int input2, int input3, int input4) {

        List<Card> playersHand = player.getPlayersHand();
        List<Card> deckOnTable = gameTable.getDeckOnTable();

        cardsForPerform.clear();
        //first elements are players cards
        cardsForPerform.add(playersHand.get(input1));
        cardsForPerform.add(playersHand.get(input2));
        //then tables
        cardsForPerform.add(deckOnTable.get(input3));
        cardsForPerform.add(deckOnTable.get(input4));
        setPerformedCards(cardsForPerform);
        return cardsForPerform;
    }
}

