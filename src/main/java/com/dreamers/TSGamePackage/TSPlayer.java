package com.dreamers.TSGamePackage;

import com.dreamers.GameCore.Player;

public class TSPlayer extends Player {

    public TSPlayer(String string) {
        this.setNickname(string);
    }

    private int gameOverSticks = 0;

    private CardCombo cardCombo() {
        return new CardCombo(playersHand);
    }

    CardCombo getCardCombo() {
        return cardCombo();
    }

    int getGameOverSticks() {
        return gameOverSticks;
    }

    void setGameOverSticks(int gameOverSticks) {
        this.gameOverSticks += gameOverSticks;
    }
}