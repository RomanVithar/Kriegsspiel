package com.game.kriegsspiel.controller;

import com.game.kriegsspiel.play.GameManager;
import com.game.kriegsspiel.play.services.GameInformation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlayController {
    private GameManager gameManager;

    public PlayController() {
        gameManager = new GameManager();
    }

    @PostMapping("addPlayer")
    @ResponseBody
    public String addPlayer(String name) {
        gameManager.addPlayer(name);
        return "Added player " + name;
    }

    @ResponseBody
    @PostMapping("startGame")
    public GameInformation startGame() {
        return gameManager.startGame();
    }

    @ResponseBody
    @PostMapping("getVision")
    public GameInformation getVision(String name) {
        return gameManager.getVision(name);
    }

    @ResponseBody
    @PostMapping("move")
    public GameInformation move(String name, int x1, int y1, int x2, int y2) {
        return gameManager.move(name, x1, y1, x2, y2);
    }
}
