package com.dreamers.TSGamePackage;

import com.dreamers.GameCore.GameTable;

import java.util.List;

class EqualPointsMiniGame {

    TSPlayer miniGameWhoGetsTheStick(GameTable gameTable, List<TSPlayer> players) {

        System.out.println("GamePoints are equal... Let's play miniSticks game");

        do {
            playersPickingCards(gameTable, players);
        } while (players.get(0).getCardCombo().gamePoints == players.get(1).getCardCombo().gamePoints);

        return whoGetsTheStickCondition(players);
    }

    private static TSPlayer whoGetsTheStickCondition(List<TSPlayer> players) {

        if (players.get(0).getCardCombo().gamePoints > players.get(1).getCardCombo().gamePoints) {
            return players.get(1);
        } else {
            return players.get(0);
        }
    }

    private static void takeAwayCurrentPlayersCards(List<TSPlayer> players) {
        players.forEach(TSPlayer::clearCards);
    }

    private void playersPickingCards(GameTable gameTable, List<TSPlayer> players) {

        takeAwayCurrentPlayersCards(players);
        for (int i = 0; i < 3; i++) {
            players.forEach(tsPlayer -> tsPlayer.pickCard(gameTable));
            players.forEach(tsPlayer -> toString());
        }
    }
}

