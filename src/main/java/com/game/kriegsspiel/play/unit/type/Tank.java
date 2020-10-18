package com.game.kriegsspiel.play.unit.type;

import com.game.kriegsspiel.play.unit.UnitType;
import com.game.kriegsspiel.play.unit.Units;

public class Tank extends Units {

    public Tank(String playerName) {
        this.playerName = playerName;
        unitType = UnitType.TANK;
        strong = 20;
        armor = 40;
    }
}
