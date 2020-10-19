package com.game.kriegsspiel.play;

import com.game.kriegsspiel.play.unit.Units;

public class GameMap {
    private final int mapSize = 80;
    private Units[][] map = new Units[mapSize][mapSize];

    public Units getUnit(int i, int j) {
        if (i < 0 || j < 0 || i >= mapSize || j >= mapSize) {
            return null;
        }
        return map[i][j];
    }

    public void setUnit(int i, int j, Units units) {
        map[i][j] = units;
    }

    public int getMapSize() {
        return mapSize;
    }
}
