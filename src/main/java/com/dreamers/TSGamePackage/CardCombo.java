package com.dreamers.TSGamePackage;

import com.dreamers.GameCore.Helper;
import com.dreamers.GameCore.Card;

import java.util.ArrayList;
import java.util.List;

class CardCombo {

    int gamePoints;
    CardCombination cardCombination;

    enum CardCombination {
        AAA, MINUSONE, ARMOR, HIGH, DEFAULT, LOW, SAMESUIT
    }

    CardCombo(List<Card> cards) {
        setPlayersCardCombo(cards);
    }

    //checking what combination of cards player have, then calculate game points
    private void setPlayersCardCombo(List<Card> cards){

        //declare vars to manipulate values easier
        //ranks
        List<Card.CardRank> cardRanks = new ArrayList<>();
        cards.forEach(card -> cardRanks.add(card.getCardRank()));
        //values
        List<Integer> cardValues = new ArrayList<>();
        cardRanks.forEach(cardRank -> cardValues.add(cardRank.getCardValue()));
        //suits
        List<Card.CardSuit> cardSuits = new ArrayList<>();
        cards.forEach(card -> cardSuits.add(card.getCardSuit()));

        //setting combination when it satisfies one of conditions
        if (Helper.allObjEqual(cardRanks) && cardRanks.get(0)== Card.CardRank.ACE) {
            gamePoints = 31;
            cardCombination = CardCombination.AAA;
        }
        else if (Helper.allObjEqual(cardRanks) && cardRanks.get(0) == Card.CardRank.SIX) {
            gamePoints = 31;
            cardCombination = CardCombination.MINUSONE;
        }
        else if ((cardRanks.contains(Card.CardRank.SIX) && cardRanks.contains(Card.CardRank.SEVEN) &&
                cardRanks.contains(Card.CardRank.EIGHT)) && (Helper.allObjEqual(cardSuits))) {
            gamePoints = 31;
            cardCombination = CardCombination.ARMOR;
        }
        else if (Helper.allObjEqual(cardSuits) && Helper.sum(cardValues) == 31){
            gamePoints = 31;
            cardCombination = CardCombination.HIGH;
        }
        else if (Helper.allObjEqual(cardSuits)){
            gamePoints = cardValues.get(0) + cardValues.get(1) + cardValues.get(2);
            cardCombination = CardCombination.SAMESUIT;
        }
        else if(twoEqualObj(cardSuits)) {
            List<Integer> indexList = getEqualCardSuitsIndex(cardSuits);
            gamePoints = cardValues.get(indexList.get(0)) + cardValues.get(indexList.get(1));
            cardCombination = CardCombination.DEFAULT;
        }
        else {
            gamePoints = cardValues.get(Helper.maxValueIndex(cardValues));
            cardCombination = CardCombination.LOW;
        }
    }

    private boolean twoEqualObj(List<Card.CardSuit> cardSuits) {
        Card.CardSuit first = cardSuits.get(0);
        for (int i = 1; i<cardSuits.size();i++){
            if (cardSuits.get(i).equals(first))
                return true;
        }
        return false;
    }

    private List<Integer> getEqualCardSuitsIndex(List<Card.CardSuit> cardSuits) {
        List<Integer> indexes = new ArrayList<>();
        final Card.CardSuit first = cardSuits.get(0);

        for (int i=1;i<cardSuits.size();i++)
            if (first == cardSuits.get(i)){
                indexes.add(0);
                indexes.add(i);
        }

        return indexes;
    }
}
