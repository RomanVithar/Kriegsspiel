package com.game.kriegsspiel.play.services;

import com.game.kriegsspiel.play.Player;
import com.game.kriegsspiel.play.unit.Units;

import java.util.ArrayList;
import java.util.List;

public class GameInformation {
    public Player player;//отсюда возмём координаты своих войск
    public  List<Units> units;//отсюда возмём вражеские(или свои которые распологаются рядом) юниты если они есть

    public GameInformation() {
        units = new ArrayList<>();
    }
}
