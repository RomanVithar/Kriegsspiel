package com.game.kriegsspiel.play;

import com.game.kriegsspiel.play.services.GameConstant;
import com.game.kriegsspiel.play.services.GameInformation;
import com.game.kriegsspiel.play.services.GameMath;
import com.game.kriegsspiel.play.unit.Units;
import com.game.kriegsspiel.play.unit.type.Infantry;
import com.game.kriegsspiel.play.unit.type.Tank;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private Map<String, Player> playerList;
    private Deque<String> playerNames;
    private GameMap map;

    public GameManager() {
        playerList = new HashMap<>(2);
        playerNames = new ArrayDeque<>(2);
        map = new GameMap();
    }

    public void addPlayer(String name) {
        playerList.put(name, new Player(name));
    }

    public GameInformation startGame() {
        GameInformation gi = new GameInformation();
        if(playerList.keySet().size()<2){
            gi.setMessageResponse("В игре должно учавствовать как минимум 2 игрока");
            return gi;
        }
        playerNames.addAll(playerList.keySet());
        arrangeRegions();
        arrangeUnits();
        return gi;
    }

    /**
     * Method which move units from coordinate x1, y1, to
     * coordinate x2, y2. If coordinate x2, y2, have
     * enemy then units will fight with them.
     *
     * @param playerName - the name player who units will move.
     * @param x1         - the x coordinate from which to move units.
     * @param y1         - the y coordinate from which to move units.
     * @param x2         - the x coordinate to move to.
     * @param y2         - the y coordinate to move to.
     *
     * @return - return information about vision available that player
     */
    public GameInformation move(String playerName, int x1, int y1, int x2, int y2) {
        GameInformation gameInfo = new GameInformation();
        if (!isThatPlayerTurn(playerName)) {
            gameInfo.setMessageResponse("Сейчас ход другого игрока");
            return gameInfo;
        }
        if (map.getUnit(y1, x1) == null) {
            gameInfo.setMessageResponse("В этой точке нет ваших отрядов");
            return gameInfo;
        }
        if (!map.getUnit(y1, x1).getPlayerName().equals(playerName)) {
            gameInfo.setMessageResponse("На чужой кусок не разевай роток");
            return gameInfo;
        }
        if (Math.abs(x2 - x1) > map.getUnit(y1, x1).getLengthMove()
                || Math.abs(y2 - y1) > map.getUnit(y1, x1).getLengthMove()) {
            gameInfo.setMessageResponse("Данный отряд не может переместиться в эту точку");
            return gameInfo;
        }
        if (map.getUnit(y2, x2) != null) {
            if (!map.getUnit(y2, x2).getPlayerName().equals(playerName)) {
                for (int i = 0; i < playerList.get(map.getUnit(y2, x2).getPlayerName()).units.size(); i++) {
                    if (playerList.get(map.getUnit(y2, x2).getPlayerName()).units.get(i).getPosition().x == x2 &&
                            playerList.get(map.getUnit(y2, x2).getPlayerName()).units.get(i).getPosition().y == y2) {
                        playerList.get(map.getUnit(y2, x2).getPlayerName()).units.remove(i);
                    }
                }
                for (int i = 0; i < playerList.get(playerName).units.size(); i++) {
                    if (playerList.get(playerName).units.get(i).getPosition().x == x1 &&
                            playerList.get(playerName).units.get(i).getPosition().y == y1) {
                        playerList.get(playerName).units.remove(i);
                    }
                }
                map.setUnit(y2, x2, GameMath.calculateWhoAlive(map.getUnit(y1, x1), map.getUnit(y2, x2)));
                map.setUnit(y1, x1, null);
                playerList.get(map.getUnit(y2, x2).getPlayerName()).units.add(map.getUnit(y2, x2));
                nextTurn();
            } else {
                gameInfo.setMessageResponse("Пока не доступное перемещение");
                return gameInfo;
            }
        } else {
            if (y2 < map.getMapSize() && y2 >= 0 && x2 < map.getMapSize() && x2 >= 0) {
                map.setUnit(y2, x2, map.getUnit(y2, x2));
                map.setUnit(y1, x1, null);
                nextTurn();
            } else {
                gameInfo.setMessageResponse("Выход за предеы карты");
                return gameInfo;
            }
        }
        return getVision(playerName);
    }

    public GameInformation getVision(String playerName) {
        GameInformation gameInfo = new GameInformation();
        GameMath.determineInfReceived(playerList.get(playerName), gameInfo);
        for (Units myUnit : gameInfo.myUnits) {
            int i = myUnit.getPosition().y;
            int j = myUnit.getPosition().x;
            if (map.getUnit(i - 1, j) != null) {
                if (!map.getUnit(i - 1, j).getPlayerName().equals(playerName)) {
                    gameInfo.enemyUnits.add(map.getUnit(i - 1, j));
                }
            }
            if (map.getUnit(i + 1, j) != null) {
                if (!map.getUnit(i + 1, j).getPlayerName().equals(playerName)) {
                    gameInfo.enemyUnits.add(map.getUnit(i + 1, j));
                }
            }
            if (map.getUnit(i, j - 1) != null) {
                if (!map.getUnit(i, j - 1).getPlayerName().equals(playerName)) {
                    gameInfo.enemyUnits.add(map.getUnit(i, j - 1));
                }
            }
            if (map.getUnit(i, j + 1) != null) {
                if (!map.getUnit(i, j + 1).getPlayerName().equals(playerName)) {
                    gameInfo.enemyUnits.add(map.getUnit(i, j + 1));
                }
            }
            if (map.getUnit(i + 1, j + 1) != null) {
                if (!map.getUnit(i + 1, j + 1).getPlayerName().equals(playerName)) {
                    gameInfo.enemyUnits.add(map.getUnit(i + 1, j + 1));
                }
            }
            if (map.getUnit(i + 1, j - 1) != null) {
                if (!map.getUnit(i + 1, j - 1).getPlayerName().equals(playerName)) {
                    gameInfo.enemyUnits.add(map.getUnit(i + 1, j - 1));
                }
            }
            if (map.getUnit(i - 1, j + 1) != null) {
                if (!map.getUnit(i - 1, j + 1).getPlayerName().equals(playerName)) {
                    gameInfo.enemyUnits.add(map.getUnit(i - 1, j + 1));
                }
            }
            if (map.getUnit(i - 1, j - 1) != null) {
                if (!map.getUnit(i - 1, j - 1).getPlayerName().equals(playerName)) {
                    gameInfo.enemyUnits.add(map.getUnit(i - 1, j - 1));
                }
            }
        }
        return gameInfo;
    }

    private void arrangeUnits() {
        for (String name : playerNames) {
            for (int i = 0; i < GameConstant.NUMBER_INFANTRY; i++) {
                Units units = new Infantry(name);
                fillMapUnitsBy(GameConstant.QUANTITY_INFANTRY, units);
                playerList.get(name).units.add(units);
            }
            for (int i = 0; i < GameConstant.NUMBER_TANK; i++) {
                Units units = new Tank(name);
                fillMapUnitsBy(GameConstant.QUANTITY_TANK, units);
                playerList.get(name).units.add(units);
            }
        }
    }

    private void fillMapUnitsBy(int quantity, Units units) {
        int x = (int) (Math.random() * map.getMapSize());
        int y = (int) (Math.random() * map.getMapSize());
        while (map.getUnit(y, x) != null) {
            x = (int) (Math.random() * map.getMapSize());
            y = (int) (Math.random() * map.getMapSize());
        }
        map.setUnit(y, x, units);
        units.setPosition(new Point(x, y));
        units.setQuantity(quantity);
    }

    private void arrangeRegions() {
          /*
        TODO расставить регионы
         */
    }

    private boolean isThatPlayerTurn(String name) {
        if (playerList.get(name).units.size() == 0) {
            /*
            TODO как то информируется о том что этот игрок проигал и проверяется закончена ли игра
             */
        }
        return name.equals(playerNames.peekLast());
    }

    private void nextTurn() {
        playerNames.addFirst(playerNames.pollLast());
    }
}
