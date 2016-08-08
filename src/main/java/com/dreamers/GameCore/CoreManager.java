package com.dreamers.GameCore;

import com.dreamers.Moves.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/main")
class CoreManager {

    //change to iOC
    //private ICoreManagerGame gameManager= ServiceContainer.Resolve<ICoreManagerGame> ();
    private CoreManagerDelegate gameManager;

    //remove in iOC as unnecessary
    CoreManager(CoreManagerDelegate gameManager) {
        this.gameManager = gameManager;
    }
    CoreManager(){
        //
    }

    @RequestMapping(value = "/start", method = GET)
    void startGame() {

        //getting initial game configuration
        //...

        //getting game credentials
        //TODO can I write some info through socket here?
        List<Player> players = gameManager.getPlayersQuantityAndNicknames();
        new ResponseEntity<>(players,HttpStatus.OK);

        //game will be played until its over
        while (!gameManager.gameOver()) {

            //we can make preparations before for every round
            gameManager.prepareForNextRound();

            //constructing deck
            gameManager.detectAndFillDeck();

            //requesting dealer
            Player dealer = gameManager.detectDealer();

            //dealing cards
            gameManager.dealCards(dealer);

            //starting game round
            while (!gameManager.endRound()) {

                //will start turn
                //..

                //will read player
                //..

                //reading player
                Player playerThatMoves = gameManager.detectActivePlayer();

                //did read player (player)
                //..

                //will read move
                //..

                //reading move from player
                Move move = gameManager.readMoveFromPlayer(playerThatMoves);

                //did read move
                //..

                //checking special pre move conditions (will perform move) bool shouldContinue
                //..

                //performing move
                gameManager.performPlayerMove(move);

                //checking special post move conditions (did end performing move)
                //..

                //checking special end move conditions
                gameManager.checkingSpecialConditions();

                //did finish turn
                //..//
            }

            //updating scoreboard after every round
            gameManager.updateScoreboard();

        }

    }
}
