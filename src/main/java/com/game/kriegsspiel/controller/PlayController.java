package com.game.kriegsspiel.controller;

import com.game.kriegsspiel.play.GameManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "Suc "+ name;
    }

    @ResponseBody
    @PostMapping("getVision")
    public void getVision() {

    }

    @ResponseBody
    @PostMapping("move")
    public void move() {

    }
}
