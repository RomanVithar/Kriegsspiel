package com.game.kriegsspiel.play.services;

import com.game.kriegsspiel.play.unit.Units;

import java.util.ArrayList;
import java.util.List;

public class GameMath {
    private static final double chanceOnDeliveryInf = 0.9;

    public static void determineInfReceived(GameInformation gameInf) {
        List<Units> newUnits = new ArrayList<>();
        for (int i = 0; i < gameInf.player.units.size(); i++) {
            if (Math.random() < chanceOnDeliveryInf) {
                newUnits.add(gameInf.player.units.get(i));
            }
        }
        gameInf.player.units = newUnits;
    }

    public static Units calculateWhoAlive(Units unit1, Units unit2){
        if(unit1.getQuantity()*unit1.getStrong()>=unit2.getQuantity()*unit2.getStrong()){
            unit1.setQuantity((int)(unit1.getQuantity() - unit1.getQuantity()*0.01d*(unit2.getStrong()*10d/unit1.getArmor())));
            return unit1;
        }else{
            unit2.setQuantity((int)(unit2.getQuantity() - unit2.getQuantity()*0.01d*(unit1.getStrong()*10d/unit2.getArmor())));
            return unit2;
        }
    }
}