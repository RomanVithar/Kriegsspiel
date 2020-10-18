package com.game.kriegsspiel.play;

import com.game.kriegsspiel.play.unit.Units;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<Units> units;
    private String name;

    public Player(String name) {
        this.name = name;
        units = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
