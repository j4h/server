package com.dreamers.core;

public class Printer {

    protected String getMessage() {
        return message;
    }

    protected String setMessage(String message) {
        this.message = message;
        return message;
    }

    public String showMessage(String message){
        System.out.println(message);
        return message;
    }

    private String message;

    //help class, to print out some useful info


    public String printCardsForMove(GameTable gameTable, Player player) {
        setMessage(player.toString() +"\n"+ gameTable.toString());
        return message;
    }

    public String printCardsBeforePutInGarbage(GameTable gameTable) {
        setMessage("These cards were put in garbage: " + gameTable.deckOnTable.toString());
        return message;
    }
}
