package com.dreamers.TSGamePackage;

import com.dreamers.GameCore.GameTable;

import java.util.List;

class Scoreboard {

    void countPoints(List<TSPlayer> players, GameTable gameTable, TSPrinter TSPrinter) {

        ScoreboardHelper helper = new ScoreboardHelper();
        TSPrinter.showAllPlayersCards(players);
        TSPlayer tsPlayer = helper.definePlayerWithMinimumPoints(players, gameTable);
        getsTheStick(tsPlayer);
        TSPrinter.showScore(players);
    }

    //setting a game over point to defined loser
    private void getsTheStick(TSPlayer player) {

        if (player != null) {
            player.setGameOverSticks(1);
            System.out.println("\nPlayer " + player.getNickname() + " gets the stick\n");
            if (player.getGameOverSticks() == 3)
                System.out.println("Player " + player.getNickname() + " has lost the game!\n");
        }
    }

}
