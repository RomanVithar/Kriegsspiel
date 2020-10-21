package com.game.kriegsspiel.services;

import com.game.kriegsspiel.play.Player;
import com.game.kriegsspiel.play.unit.Units;

import java.util.ArrayList;
import java.util.List;

public class GameInformation {
    public List<Units> myUnits;
    public List<Units> enemyUnits;
    public String messageResponse;

    public GameInformation() {
        enemyUnits = new ArrayList<>();
        myUnits = new ArrayList<>();
    }

    public void setMessageResponse(String messageResponse) {
        this.messageResponse = messageResponse;
    }
}
