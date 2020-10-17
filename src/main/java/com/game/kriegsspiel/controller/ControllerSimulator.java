package com.game.kriegsspiel.controller;

import com.game.kriegsspiel.play.GameManager;
import com.game.kriegsspiel.play.Player;

public class ControllerSimulator {

    public static void main(String[] args) {
        GameManager manager = new GameManager();

        manager.addPlayer("Bob");
        manager.addPlayer("Alice");

        manager.startGame();

    }
}
