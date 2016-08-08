package com.dreamers.TSGamePackage;

import com.dreamers.GameCore.Card;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class ReaderHelper {

    private Scanner userKeyboard = new Scanner(System.in);
    private String userInput;
    private Scanner inputScanner;

    int userInput1, userInput2, userInput3, userInput4;

    private void getSomeUserInput() {

        userInput = userKeyboard.nextLine();
        inputScanner = new Scanner(userInput);
    }

    int isIntegerInput() {

        if (inputScanner.hasNextInt()) {
            return Character.getNumericValue(userInput.charAt(0));
        }

        return -1;
    }

    private boolean validMoveTypeInput() {

        return ((isIntegerInput() != -1) && (validMoveTypeInput(isIntegerInput()) && (userInput.length() == 1)));
    }

    private void moveTypeInputErrorLoop(TSPrinter TSPrinter) {

        do {
            TSPrinter.showMessage(TSPrinter.moveTypeInputError());
            getValidMoveTypeInput(TSPrinter);
        } while (!validMoveTypeInput(isIntegerInput()));
    }

    void getValidMoveTypeInput(TSPrinter TSPrinter) {

        getSomeUserInput();

        if (!validMoveTypeInput()) {
            moveTypeInputErrorLoop(TSPrinter);
        }
    }

    private boolean validMoveTypeInputWithoutDS() {

        return ((isIntegerInput() != -1) && (validMoveTypeInputWithoutDS(isIntegerInput()) && (userInput.length() == 1)));
    }

    void getValidMoveTypeInputWithoutDS(TSPrinter TSPrinter) {

        getSomeUserInput();

        if (!validMoveTypeInputWithoutDS())
            moveTypeInputErrorLoopWithoutDS(TSPrinter);
    }

    private void moveTypeInputErrorLoopWithoutDS(TSPrinter TSPrinter) {

        do {
            TSPrinter.showMessage(TSPrinter.moveTypeInputErrorWithoutDS());
            getValidMoveTypeInputWithoutDS(TSPrinter);
        } while (!validMoveTypeInputWithoutDS(isIntegerInput()));
    }

    char suggestBlindChangeMove() {

        getSomeUserInput();

        if (!Objects.equals(userInput, " "))
            return userInput.charAt(0);

        return 's';
    }

    private boolean validSSInput() {

        if (inputScanner.hasNextInt() && userInput.length() == 3) {

            if (validSSMoveInput(0, 3, Character.getNumericValue(userInput.charAt(0)),
                    Character.getNumericValue(userInput.charAt(2)))) {

                userInput1 = Character.getNumericValue(userInput.charAt(0)) - 1;
                userInput2 = Character.getNumericValue(userInput.charAt(2)) - 1;

                return true;
            }
        }

        return false;
    }

    private void ssMoveInputErrorLoop(TSPrinter TSPrinter) {

        do {
            TSPrinter.showMessage(TSPrinter.ssMoveError());
            getValidSSMoveInput(TSPrinter);
        } while (!validSSMoveInput(0, 3, userInput1, userInput2));
    }

    void getValidSSMoveInput(TSPrinter TSPrinter) {

        getSomeUserInput();

        if (!validSSInput())
            ssMoveInputErrorLoop(TSPrinter);
    }

    private boolean validDSInput(TSGameTable gameTable, TSPlayer player) {

        if (inputScanner.hasNextInt() && userInput.length() == 5) {
            int inputCopy = Character.getNumericValue(userInput.charAt(0));
            int inputCopy2 = Character.getNumericValue(userInput.charAt(1));
            int inputCopy3 = Character.getNumericValue(userInput.charAt(3));
            int inputCopy4 = Character.getNumericValue(userInput.charAt(4));

            if (validDSInput(inputCopy, inputCopy2, inputCopy3, inputCopy4) && (inputCopy != inputCopy2 &&
                    inputCopy3 != inputCopy4)) {
                userInput1 = inputCopy - 1;
                userInput2 = inputCopy2 - 1;
                userInput3 = inputCopy3 - 1;
                userInput4 = inputCopy4 - 1;

                if (validDSSuitInput(gameTable, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void dsMoveErrorLoop(TSGameTable gameTable, TSPlayer player, TSPrinter TSPrinter) {

        do {
            TSPrinter.showMessage(TSPrinter.dsMoveError());
            getValidDSMoveInput(gameTable, player, TSPrinter);
        } while (!validDSSuitInput(gameTable, player));

    }

    void getValidDSMoveInput(TSGameTable gameTable, TSPlayer player, TSPrinter TSPrinter) {

        getSomeUserInput();

        if (!validDSInput(gameTable, player))
            dsMoveErrorLoop(gameTable, player, TSPrinter);
    }

    boolean dsMovePossibility(TSGameTable gameTable, TSPlayer player) {
        return (player.getCardCombo().cardCombination != CardCombo.CardCombination.AAA &&
                player.getCardCombo().cardCombination != CardCombo.CardCombination.LOW &&
                player.getCardCombo().cardCombination != CardCombo.CardCombination.MINUSONE) &&
                gameTable.cardCombo().cardCombination == CardCombo.CardCombination.DEFAULT;
    }

    private boolean validDSSuitInput(TSGameTable gameTable, TSPlayer player) {

        List<Card> playersHand = player.getPlayersHand();
        List<Card> deckOnTable = gameTable.getDeckOnTable();

        return deckOnTable.get(userInput3).getCardSuit() == deckOnTable.get(userInput4).getCardSuit()
                && playersHand.get(userInput1).getCardSuit() == (playersHand.get(userInput2).getCardSuit());

    }

    private boolean validDSInput(int pc1, int pc2, int tc1, int tc2) {

        return (pc1 <= 3 && pc1 >= 0) && (tc1 <= 3 && tc1 >= 0) && (pc2 <= 3 && pc2 >= 0) && (tc2 <= 3 && tc2 >= 0);
    }

    private boolean validSSMoveInput(int fromInt, int tillInt, int userInput1, int userInput2) {

        return (userInput1 >= fromInt && userInput1 <= tillInt) && (userInput2 >= fromInt && userInput2 <= tillInt);
    }

    private boolean validMoveTypeInput(int chosenTypeOfMove) {

        return chosenTypeOfMove == 0 || chosenTypeOfMove == 1 || chosenTypeOfMove == 2;
    }

    private boolean validMoveTypeInputWithoutDS(int chosenTypeOfMove) {

        return chosenTypeOfMove == 0 || chosenTypeOfMove == 1;

    }

}
