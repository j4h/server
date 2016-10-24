package com.dreamers.threeSticks;

import com.dreamers.moves.ts.DoubleShuffleMove;
import com.dreamers.moves.Move;
import com.dreamers.moves.ts.SingleShuffleMove;
import com.dreamers.moves.SkipMove;

class Reader {

    private ReaderHelper helper = new ReaderHelper();

    //reading move from player
    Move readMoveFromPlayer(TSGameTable gameTable, TSPlayer player, TSPrinter TSPrinter,
                            TSMovePerformDataSource tsMovePerformDataSource) {

        Move move;
        System.out.println("Choose a type of move: ");
        TSPrinter.showMessage(TSPrinter.printCardsForMove(gameTable, player));

        //checking possibility of DoubleShuffle
        if (helper.dsMovePossibility(gameTable, player)) {
           move = readMoveWithDS(gameTable,player, TSPrinter, tsMovePerformDataSource);
        } else {
            //without DS
            move = readMoveWithOutDS(gameTable,player, TSPrinter, tsMovePerformDataSource);
        }
        return move;
    }

    //reading move with DoubleShuffle possibility
    private Move readMoveWithDS(TSGameTable gameTable, TSPlayer player, TSPrinter TSPrinter,
                                TSMovePerformDataSource tsMovePerformDataSource) {

        Move move;
        TSPrinter.showMessage(TSPrinter.printMoveTypesInfo());
        helper.getValidMoveTypeInput(TSPrinter);
        //reading moves from user input
        switch (helper.isIntegerInput()) {
            case 0:
                move = new SkipMove();
                break;
            case 1:
                move = readSSMove(gameTable, player, TSPrinter, tsMovePerformDataSource);
                break;
            case 2:
                move = readDSMove(gameTable, player, TSPrinter, tsMovePerformDataSource);
                break;
            default:
                move = new SkipMove();
                break;
        }
        return move;
    }

    private Move readMoveWithOutDS(TSGameTable gameTable, TSPlayer player, TSPrinter TSPrinter,
                                   TSMovePerformDataSource tsMovePerformDataSource) {

        Move move;
        TSPrinter.showMessage(TSPrinter.printMoveTypesInfoWithoutDSMove());
        helper.getValidMoveTypeInputWithoutDS(TSPrinter);
        switch (helper.isIntegerInput()) {
            case 0:
                move = new SkipMove();
                break;
            case 1:
                move = readSSMove(gameTable, player, TSPrinter, tsMovePerformDataSource);
                break;
            default:
                move = new SkipMove();
                break;
        }
        return move;
    }
    //creating moves
    private SingleShuffleMove readSSMove(TSGameTable gameTable, TSPlayer player, TSPrinter TSPrinter,
                                         TSMovePerformDataSource tsMovePerformDataSource) {

        TSPrinter.showMessage(TSPrinter.ssMoveInstructions());
        helper.getValidSSMoveInput(TSPrinter);
        tsMovePerformDataSource.createDataForSingleMove(gameTable, player, helper.userInput1, helper.userInput2);
        return new SingleShuffleMove();
    }

    private DoubleShuffleMove readDSMove(TSGameTable gameTable, TSPlayer player, TSPrinter TSPrinter,
                                         TSMovePerformDataSource tsMovePerformDataSource) {

        TSPrinter.showMessage(TSPrinter.dsMoveInstructions());
        helper.getValidDSMoveInput(gameTable, player, TSPrinter);
        tsMovePerformDataSource.createDataForDoubleMove(gameTable, player, helper.userInput1, helper.userInput2,
                helper.userInput3, helper.userInput4);
        return new DoubleShuffleMove();
    }

    char suggestBlindChangeMove() {
      return helper.suggestBlindChangeMove();
    }

}
