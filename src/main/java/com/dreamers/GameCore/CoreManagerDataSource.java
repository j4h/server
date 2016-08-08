package com.dreamers.GameCore;

import com.dreamers.Moves.Move;

import java.util.List;

public interface CoreManagerDataSource {

    List<Player> getPlayersQuantityAndNicknames(); //from client

    //TODO need solution about response here
    boolean endRound(); //from server

    boolean gameOver(); //

    List<Card> detectAndFillDeck(); //need be from server; must add here what game to play method

    Player detectDealer(); //from server

    //TODO change to List of Cards in return
    Player dealCards(Player dealer); //from server

    Player detectActivePlayer(); //from server

    Move readMoveFromPlayer(Player activePlayer); //from client

    //void validatePlayerMove(); //from server

    //TODO need solutions about notifications here
    void performPlayerMove(Move move); //from server /list of changed cards, or just 201 status

    void checkingSpecialConditions(); //from server /sending notifications about possible events

    void updateScoreboard(); //from server /information about scoreboard as a table

    void prepareForNextRound(); //from server

}
