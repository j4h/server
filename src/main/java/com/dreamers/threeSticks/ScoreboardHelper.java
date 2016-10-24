package com.dreamers.threeSticks;

import com.dreamers.core.Helper;
import com.dreamers.core.GameTable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ScoreboardHelper {

    TSPlayer definePlayerWithMinimumPoints(List<TSPlayer> players, GameTable gameTable) {

        //checking special card combinations that gives extra cookies
        checkingSpecialCombo(players);
        //return variable
        final TSPlayer player;
        //init points array
        List<Integer> gp = initGamePoints(players);
        final int indexMin = Helper.minValueIndex(gp);

        if (everyoneHas31(players)) {
            System.out.println("No one gets the stick. Congratulations =)");
        } else {
            //if points are equal - we starting mini game, if not return player with min value
            if (needMiniGame(gp,indexMin)) {
                EqualPointsMiniGame equalPointsMiniGame = new EqualPointsMiniGame();
                List<TSPlayer> twoPlayers = initTwoPlayersArray(players, gp, indexMin);
                player = equalPointsMiniGame.miniGameWhoGetsTheStick(gameTable, twoPlayers);
            } else {
                player = players.get(indexMin);
            }
            return player;
        }
        return null;
    }

    private void checkingSpecialCombo(List<TSPlayer> players) {

        //checking AAA -3 gameOverSticks combo
        players.stream().filter(tsPlayer -> tsPlayer.getCardCombo().cardCombination ==
                CardCombo.CardCombination.AAA).forEach(tsPlayer -> {
            tsPlayer.setGameOverSticks(-3);
            System.out.println("Player " + tsPlayer.getNickname() + " gets minus 3 sticks.");
        });

        //checking SIX SIX SIX -1 gameOverStick combo
        players.stream().filter(tsPlayer -> tsPlayer.getCardCombo().cardCombination ==
                CardCombo.CardCombination.MINUSONE).forEach(tsPlayer -> {tsPlayer.setGameOverSticks(-1);
            System.out.println("Player " + tsPlayer.getNickname() + " gets minus 1 stick.");
        });
    }

    private boolean needMiniGame(List<Integer> gp, int indexMin) {

        //isn't redundant, cuz if we delete the element from parameter
        //then we need to add it in 2 cases, and we don't care about copy
        List<Integer> gpCopy = gp;
        int lowestElement = gp.get(indexMin);
        gpCopy.remove(indexMin);

        for (Integer aGp : gpCopy) {
            if (aGp == lowestElement) {
                return true;
            }
        }
        return false;
    }

    private TSPlayer playerWithEqualPoints(List<Integer> gp, int indexMin, List<TSPlayer> players) {

        //copying comparable element
        int lowestElementValue = gp.get(indexMin);

        for (TSPlayer tsPlayer:players) {
            if (tsPlayer.getCardCombo().gamePoints == lowestElementValue &&
                    !players.get(indexMin).equals(tsPlayer))
                return players.get(players.indexOf(tsPlayer));
        }

        return null;
    }

    private List<TSPlayer> initTwoPlayersArray(List<TSPlayer> players, List<Integer> gp, int indexMin) {

        List<TSPlayer> players1 = new LinkedList<>();
        players1.add(players.get(indexMin));
        players1.add(playerWithEqualPoints(gp, indexMin, players));

        return players1;
    }

    private List<Integer> initGamePoints (List<TSPlayer> players) {
        List<Integer> list = new ArrayList<>();
        list.add(players.get(0).getCardCombo().gamePoints);
        list.add(players.get(1).getCardCombo().gamePoints);
        list.add(players.get(2).getCardCombo().gamePoints);
        //code with lambda below doesn't work properly (bad with positioning elements)
       // return players.stream().map(tsPlayer -> tsPlayer.getCardCombo().gamePoints).collect(Collectors.toList());
        return list;
    }

    private boolean everyoneHas31(List<TSPlayer> players) {
        List<TSPlayer> playersCopy = new ArrayList<>();
        players.stream().filter(tsPlayer -> tsPlayer.getCardCombo().gamePoints == 31).forEach(playersCopy::add);
        return playersCopy.size() == players.size();
    }

}
