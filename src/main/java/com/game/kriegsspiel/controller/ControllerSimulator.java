package com.game.kriegsspiel.controller;

import com.game.kriegsspiel.play.GameManager;
import com.game.kriegsspiel.play.services.GameInformation;

import java.util.Scanner;

public class ControllerSimulator {

    public static void main(String[] args) {
        GameManager manager = new GameManager();

        manager.addPlayer("Bob");
        manager.addPlayer("Alice");

        manager.startGame();
        System.out.println("The game is created");

        GameInformation gi = manager.getVision("Alice");
        Scanner scanner = new Scanner(System.in);
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        manager.move("Alice",x1,y1,x2,y2);
        System.out.println("check");
    }
}
