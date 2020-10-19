package com.game.kriegsspiel.play;

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

    private final int NUMBER_INFANTRY = 7;
    private final int NUMBER_TANK = 4;
    private final int QUANTITY_INFANTRY = 100;
    private final int QUANTITY_TANK = 20;


    public GameManager() {
        playerList = new HashMap<>(2);
        playerNames = new ArrayDeque<>(2);
        map = new GameMap();
    }

    public void addPlayer(String name) {
        playerList.put(name, new Player(name));
    }

    public void startGame() {
        playerNames.addAll(playerList.keySet());
        arrangeRegions();
        arrangeUnits();
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
     */
    public void move(String playerName, int x1, int y1, int x2, int y2) {
        if (isThatPlayerTurn(playerName)) {
            if (map.getUnit(y1, x1) != null) {
                if (map.getUnit(y1, x1).getPlayerName().equals(playerName)) {
                    if (map.getUnit(y2, x2) != null) {
                        if (!map.getUnit(y2, x2).getPlayerName().equals(playerName)) {
                            for(int i=0;i<playerList.get(map.getUnit(y2,x2).getPlayerName()).units.size();i++){
                                if(playerList.get(map.getUnit(y2,x2).getPlayerName()).units.get(i).getPosition().x == x2 &&
                                        playerList.get(map.getUnit(y2,x2).getPlayerName()).units.get(i).getPosition().y==y2){
                                    playerList.get(map.getUnit(y2,x2).getPlayerName()).units.remove(i);
                                }
                            }
                            for(int i=0;i<playerList.get(playerName).units.size();i++){
                                if(playerList.get(playerName).units.get(i).getPosition().x == x1 &&
                                        playerList.get(playerName).units.get(i).getPosition().y==y1){
                                    playerList.get(playerName).units.remove(i);
                                }
                            }
                            map.setUnit(y2,x2,GameMath.calculateWhoAlive(map.getUnit(y1,x1),map.getUnit(y2,x2)));
                            map.setUnit(y1,x1,null);
                            playerList.get(map.getUnit(y2,x2).getPlayerName()).units.add(map.getUnit(y2,x2));
                            nextTurn();
                        } else {
                            System.out.println("Пока не доступное перемещение");
                        }
                    } else {
                        if (y2 < map.getMapSize() && y2 >= 0 && x2 < map.getMapSize() && x2 >= 0) {
                            map.setUnit(y2, x2, map.getUnit(y2, x2));
                            map.setUnit(y1,x1, null);
                            nextTurn();
                        }else{
                            System.out.println("Выход за предеы карты");
                        }
                    }
                } else {
                    System.out.println("На чужой кусок не разевай роток");
                }
            } else {
                System.out.println("В этой точке нет ваших отрядов");
            }
        } else {
            System.out.println("Сейчас ход другого игрока");
        }
    }

    public GameInformation getVision(String playerName) {
        if (isThatPlayerTurn(playerName)) {
            GameInformation gameInfo = new GameInformation();
            gameInfo.player = playerList.get(playerName);
            GameMath.determineInfReceived(gameInfo);
            for (Units myUnit : gameInfo.player.units) {
                int i = myUnit.getPosition().y;
                int j = myUnit.getPosition().x;
                if (map.getUnit(i - 1, j) != null) {
                    if (!map.getUnit(i - 1, j).getPlayerName().equals(playerName)) {
                        gameInfo.units.add(map.getUnit(i - 1, j));
                    }
                }
                if (map.getUnit(i + 1, j) != null) {
                    if (!map.getUnit(i + 1, j).getPlayerName().equals(playerName)) {
                        gameInfo.units.add(map.getUnit(i + 1, j));
                    }
                }
                if (map.getUnit(i, j - 1) != null) {
                    if (!map.getUnit(i, j - 1).getPlayerName().equals(playerName)) {
                        gameInfo.units.add(map.getUnit(i, j - 1));
                    }
                }
                if (map.getUnit(i, j + 1) != null) {
                    if (!map.getUnit(i, j + 1).getPlayerName().equals(playerName)) {
                        gameInfo.units.add(map.getUnit(i, j + 1));
                    }
                }
                if (map.getUnit(i + 1, j + 1) != null) {
                    if (!map.getUnit(i + 1, j + 1).getPlayerName().equals(playerName)) {
                        gameInfo.units.add(map.getUnit(i + 1, j + 1));
                    }
                }
                if (map.getUnit(i + 1, j - 1) != null) {
                    if (!map.getUnit(i + 1, j - 1).getPlayerName().equals(playerName)) {
                        gameInfo.units.add(map.getUnit(i + 1, j - 1));
                    }
                }
                if (map.getUnit(i - 1, j + 1) != null) {
                    if (!map.getUnit(i - 1, j + 1).getPlayerName().equals(playerName)) {
                        gameInfo.units.add(map.getUnit(i - 1, j + 1));
                    }
                }
                if (map.getUnit(i - 1, j - 1) != null) {
                    if (!map.getUnit(i - 1, j - 1).getPlayerName().equals(playerName)) {
                        gameInfo.units.add(map.getUnit(i - 1, j - 1));
                    }
                }
            }
            return gameInfo;
        } else {
            /*
            убрать это
             */
            System.out.println("Сейчас ход другого игрока");
            return null;
        }
    }

    private void arrangeUnits() {
         /*
        TODO расставить войска
         */
        for (String name : playerNames) {
            for (int i = 0; i < NUMBER_INFANTRY; i++) {
                Units units = new Infantry(name);
                fillMapUnitsBy(QUANTITY_INFANTRY, units);
                playerList.get(name).units.add(units);
            }
            for (int i = 0; i < NUMBER_TANK; i++) {
                Units units = new Tank(name);
                fillMapUnitsBy(QUANTITY_TANK, units);
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
        if(playerList.get(name).units.size() == 0){
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
