package com.dreamers.TSGamePackage;

import com.dreamers.GameCore.*;
import com.dreamers.Moves.ts.BlindChangeMove;
import com.dreamers.Moves.Move;
import com.dreamers.Moves.SkipMove;
import com.dreamers.GameCore.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameManager implements CoreManagerDataSource, CoreManagerDelegate {

    private TSGameTable gameTable = new TSGameTable();
    private List<TSPlayer> players = new LinkedList<>();
    private Reader reader = new Reader();
    private TSMovePerformDataSource tsMovePerformDataSource = new TSMovePerformDataSource();
    private TSPrinter TSPrinter = new TSPrinter();

    private TSPlayer tsDealer;
    private TSPlayer activeTSPlayer;

    private int moveIndex;
    private int dealerIndex = 0;
    private int activePlayerIndex = 1;
    private int endRoundCounter = 0;
    private int skipMoveQuantityInSequence = 0;

    public List<Player> getPlayersQuantityAndNicknames() {

        //create fake players
        List<Player> playerss = new ArrayList<>();
        TSPlayer player, player1, player2;
        String g = "d";
        String r = "st";
        String h = "lt";
        player = new TSPlayer(g);
        player1 = new TSPlayer(r);
        player2 = new TSPlayer(h);

        players.add(player);
        players.add(player1);
        players.add(player2);
        return playerss;
       /* for (int i =0; i<playersQuantity; i++){
            System.out.print("Enter nickname, please: ");
            String name = scanner.next();
            TSPlayer player = new TSPlayer(name);
            players.add(player);
        }*/
    }

    @Override
    public List<Card> detectAndFillDeck() {
       return gameTable.fill36CardsDeck();
    }

    @Override
    public Player detectDealer() {

        if (dealerIndex == players.size()) {
            dealerIndex = 0;
        }
        tsDealer = players.get(dealerIndex);
        dealerIndex++;

        return tsDealer;
    }

    @Override
    //TODO how to send notifications till method runs
    public Player dealCards(Player player) {

        for (int i = 0; i < 3; i++)
            for (TSPlayer player1 : players) {
                if (player == player1)
                    gameTable.addCardOnTable();
                player1.pickCard(gameTable);
            }

        //suggesting blindChangeMove to dealer
        willDealCards();

        return player;
    }

    @Override
    public Player detectActivePlayer() {

        putCardsOnGarbageIfNeeded();

        if (activePlayerIndex == players.size()) {
            activePlayerIndex = 0;
        }
        if (moveIndex == 0) {
            activeTSPlayer = players.get(players.indexOf(tsDealer) + 1);
        } else {
            activeTSPlayer = players.get(activePlayerIndex);
        }

        return activeTSPlayer;
    }

    @Override
    public Move readMoveFromPlayer(Player player) {
        return reader.readMoveFromPlayer(gameTable, activeTSPlayer, TSPrinter, tsMovePerformDataSource);
    }

    @Override
    public void performPlayerMove(Move move) {

        move.perform(gameTable, activeTSPlayer, tsMovePerformDataSource);
        moveIndex++;
        //counting three skips in a row
        endRoundWithout31PointsCounter(move);
    }

    @Override
    public void checkingSpecialConditions() {

        boolean sameSuitTable = false;

        //put table cards on garbage and add new one from deck
        if (gameTable.cardCombo().cardCombination == CardCombo.CardCombination.SAMESUIT) {
            putCardsOnGarbageIfNeeded();
            sameSuitTable = true;
            ///repeat same players move
            activePlayerIndex--;
        }
        //give 2 moves when someone has 31
        if (endRoundFlag()) {
            System.out.println("Player "+activeTSPlayer.getNickname()+ " has 31 point. 2 moves will end the game.\n");
            endRoundCounter++;
            if (sameSuitTable)
                endRoundCounter--;
        }
        //next player move
        activePlayerIndex++;
    }

    @Override
    public void updateScoreboard() {

        Scoreboard scoreboard = new Scoreboard();
        scoreboard.countPoints(players, gameTable, TSPrinter);
    }

    @Override
    public void prepareForNextRound() {

        skipMoveQuantityInSequence = 0;
        gameTable.clearAllCards();
        players.forEach(TSPlayer::clearCards);
        System.out.println("Starting new round...\n");
    }

    @Override
    public void willDealCards() {

        putCardsOnGarbageIfNeeded();
        TSPrinter.showMessage(TSPrinter.printInfoForBlindMove(gameTable));
        char answer = reader.suggestBlindChangeMove();

        if (answer == 'y' || answer == 'Y') {
            Move move = new BlindChangeMove();
            move.perform(gameTable, tsDealer, tsMovePerformDataSource);
        }
    }

    @Override
    public boolean endRound() {

        return endRoundCounter == players.size() || endRoundWithoutAny31PointsCondition();
    }

    @Override
    public boolean gameOver() {

        for (TSPlayer player : players) {
            if (player.getGameOverSticks() == 3) {
                return true;
            }
        }

        return false;
    }

    private boolean endRoundWithoutAny31PointsCondition() {

        return skipMoveQuantityInSequence == players.size();
    }

    private boolean endRoundFlag() {

        for (TSPlayer player : players) {
            if (player.getCardCombo().gamePoints == 31) {
                return true;
            }
        }
        return false;
    }

    private void endRoundWithout31PointsCounter(Move move){

        //Skip moves by all players in a raw = end of round
        if (move.getClass() == SkipMove.class) {
            skipMoveQuantityInSequence++;
        }else {
            skipMoveQuantityInSequence = 0;
        }
    }

    private void putCardsOnGarbageIfNeeded() {

        while (gameTable.cardCombo().cardCombination == CardCombo.CardCombination.SAMESUIT) {
            TSPrinter.showMessage(TSPrinter.printCardsBeforePutInGarbage(gameTable));
            gameTable.putCardsOnGarbageFromTable();

            if (gameTable.deckOfCardsEmpty()) {
                gameTable.fillEmptyMainDeckFromGarbage();
            }
        }

    }
/*
    private List<Player> createFakePlayersForTestings() {
        List<Player> players = new ArrayList<>();
        TSPlayer player, player1, player2;
        String g = "d";
        String r = "st";
        String h = "lt";
        player = new TSPlayer(g);
        player1 = new TSPlayer(r);
        player2 = new TSPlayer(h);

        players.add(player);
        players.add(player1);
        players.add(player2);
        return players;
    }*/
}

