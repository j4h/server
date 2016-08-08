package com.dreamers.GameCore;

import com.dreamers.TSGamePackage.GameManager;

public class Main {

    public static void main(String[] args) {

        CoreManagerDelegate threeSticksManager = new GameManager();
        CoreManager anyGameManager = new CoreManager(threeSticksManager);
        //ui ...//...

        anyGameManager.startGame();
    }
}
