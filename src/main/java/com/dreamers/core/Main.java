package com.dreamers.core;

import com.dreamers.threeSticks.GameManager;

public class Main {

    public static void main(String[] args) {

        CoreManagerDelegate threeSticksManager = new GameManager();
        CoreManager anyGameManager = new CoreManager(threeSticksManager);
        //ui ...//...

        anyGameManager.startGame();
    }
}
