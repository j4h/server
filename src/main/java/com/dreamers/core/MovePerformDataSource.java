package com.dreamers.core;

import java.util.ArrayList;
import java.util.List;

public class MovePerformDataSource {

    //container for cards
    private List<Card> performCardsContainer = new ArrayList<>();

    public List<Card> getPerformCards() {
        return performCardsContainer;
    }

    protected List<Card> setPerformedCards(List<Card> setCards) {
        //clear list before each set
        this.performCardsContainer.clear();
        this.performCardsContainer = setCards;
        return this.performCardsContainer;
    }

}
