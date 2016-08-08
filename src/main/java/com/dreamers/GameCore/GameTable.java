package com.dreamers.GameCore;

import java.util.*;

public class GameTable {

    private Stack<Card> mainDeck = new Stack<>();
    private List<Card> deckForGarbage = new ArrayList<>();
    protected List<Card> deckOnTable = new ArrayList<>();

    private Stack<Card> setMainDeck(List<Card> cards) {
        mainDeck.clear();
        mainDeck.addAll(cards);
        return (Stack<Card>)cards;
    }

    public List<Card> getDeckOnTable() {
        return deckOnTable;
    }

    public void setDeckOnTable(List<Card> deckOnTable) {
        this.deckOnTable = deckOnTable;
    }

    protected List<Card> putCardsOnGarbage(List<Card> deckForGarbage1) {
        deckForGarbage.addAll(deckForGarbage1);
        return deckForGarbage1;
    }

    //create a shuffled deck of 36 cards
    public Stack<Card> fill36CardsDeck() {
        for (Card.CardSuit cardSuit : Card.CardSuit.values()) {
            for (Card.CardRank cardRank : Card.CardRank.values()) {

                if ((cardRank != Card.CardRank.TWO) && (cardRank != Card.CardRank.THREE) &&
                        (cardRank != Card.CardRank.FOUR) && (cardRank != Card.CardRank.FIVE)
                        && (cardRank != Card.CardRank.JOKER))
                    mainDeck.add(new Card(cardSuit, cardRank));
            }
        }
        Collections.shuffle(mainDeck);
        return mainDeck;
    }

    //functions for mainDeck
    public boolean deckOfCardsEmpty () {
        return mainDeck.empty();
    }

    public Card giveCard() {
        return mainDeck.pop();
    }

    //functions for deckOnTable
    public Card addCardOnTable() {
        Card card = giveCard();
        deckOnTable.add(card);
        return card;
    }

    public void clearAllCards(){
        mainDeck.clear();
        deckOnTable.clear();
        deckForGarbage.clear();
    }

    public Stack<Card> fillEmptyMainDeckFromGarbage() {

        Collections.shuffle(deckForGarbage);
        Stack<Card> mainDeck = (Stack<Card>) deckForGarbage;
        setMainDeck(deckForGarbage);
        deckForGarbage.clear();
        return mainDeck;
    }
}

