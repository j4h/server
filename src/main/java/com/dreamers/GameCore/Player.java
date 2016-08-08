package com.dreamers.GameCore;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String nickname;
    protected List<Card> playersHand = new ArrayList<>();

    public Player() {
        //
    }

    public List<Card> getPlayersHand() {
        return playersHand;
    }

    public void setPlayersHand(List<Card> playersHand) {
        this.playersHand = playersHand;
    }

    public String getNickname() {
        return nickname;
    }

    protected void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void pickCard(GameTable gameTable) {
        playersHand.add(gameTable.giveCard());
    }

    @Override
    public String toString() {
        return "Player " + nickname + " cards: " + playersHand;
    }

    public void clearCards() {
        playersHand.clear();
    }

}
