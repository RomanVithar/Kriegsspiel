package com.game.kriegsspiel.play.unit.type;

import com.game.kriegsspiel.play.unit.UnitType;
import com.game.kriegsspiel.play.unit.Units;

public class Infantry extends Units {

    public Infantry(String playerName) {
        this.playerName = playerName;
        unitType = UnitType.INFANTRY;
        armor = 10;
        strong = 10;
        lengthMove = 1;
    }
}
