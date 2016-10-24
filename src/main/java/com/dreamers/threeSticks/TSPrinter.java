package com.dreamers.threeSticks;

import com.dreamers.core.Printer;

import java.util.List;

class TSPrinter extends Printer {

    String showAllPlayersCards(List<TSPlayer> players) {
        players.forEach(player -> System.out.println(player.toString()));
        return null;
    }

    String showScore(List<TSPlayer> players) {

        players.forEach(player -> System.out.println("Player " + player.getNickname() + "\n" + " Points: " +
                player.getCardCombo().gamePoints + "\n" + " Sticks: " + player.getGameOverSticks() + "\n \n"));

        return null;
    }

    String printMoveTypesInfo() {
       setMessage("Type 0 to skip move \nType 1 to shuffle one your card for one card from the table \nType 2 to " +
                "shuffle two of your cards of the same suit for two cards from the table of the same suit");
        return getMessage();
    }

    String printMoveTypesInfoWithoutDSMove() {
        setMessage("Type 0 to skip move \nType 1 to shuffle one your card for one card from the table");
        return getMessage();
    }

    String printInfoForBlindMove(TSGameTable gameTable) {

        setMessage("Type 'y' if you agree, all other answers will be considered like no\n" +
                "You can blindly change all your cards for these cards: " + gameTable.getDeckOnTable().toString());
       return getMessage();

    }

    String moveTypeInputError() {

        String setMsg = setMessage("Something goes wrong! Try again, please.");
        printMoveTypesInfo();
        return getMessage() + setMsg;
    }

    String moveTypeInputErrorWithoutDS() {

        String setMsg = setMessage("Something goes wrong! Try again, please.");
        printMoveTypesInfoWithoutDSMove();
        return getMessage() + setMsg;
    }

    String ssMoveInstructions() {

        setMessage("You can shuffle any of your cards for any of cards from the table" + "\n"+
                "For example, type: 1 2 to shuffle your 1st card to 2nd card from the table");
       return getMessage();
    }

    String ssMoveError() {

        String setMsg = setMessage("Something goes wrong! Try again, please.");
        ssMoveInstructions();
        return getMessage() + setMessage(setMsg);
    }


    String dsMoveInstructions() {

        setMessage("You can shuffle 2 your cards of the same suit for 2 cards on table of the same suit\n"+
                "For example, type: 12 13 to shuffle your 1st and 2nd cards to 1st and 3rd cards from the table");
       return getMessage();
    }

    String dsMoveError() {

        String setMsg = setMessage("Something goes wrong. Your cards or cards from table wasn't of the same suit. Try again, please");
        dsMoveInstructions();
        return getMessage() + setMsg;
    }

}
