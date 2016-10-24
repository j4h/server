package com.dreamers.threeSticks;

import com.dreamers.core.GameTable;

public class TSGameTable extends GameTable {

    private void add3CardsOnTable() {
        for (int i = 0; i < 3; i++)
            addCardOnTable();
    }

    CardCombo cardCombo() {
        return new CardCombo(getDeckOnTable());
    }

    void putCardsOnGarbageFromTable() {
        putCardsOnGarbage(deckOnTable);
        deckOnTable.clear();
        add3CardsOnTable();
    }

    @Override
    public String toString() {
        return "Table cards: " + deckOnTable;
    }

}
