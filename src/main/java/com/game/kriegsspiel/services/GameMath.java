package com.game.kriegsspiel.services;

import com.game.kriegsspiel.play.Player;
import com.game.kriegsspiel.play.unit.Units;

import java.util.ArrayList;
import java.util.List;

public class GameMath {
    private static final double chanceOnDeliveryInf = 0.8;

    public static void determineInfReceived(Player player, GameInformation gameInf) {
        List<Units> newUnits = new ArrayList<>();
        for (int i = 0; i < player.units.size(); i++) {
            if (Math.random() < chanceOnDeliveryInf) {
                newUnits.add(player.units.get(i));
            }
        }
        gameInf.myUnits = newUnits;
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