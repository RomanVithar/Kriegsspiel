package com.game.kriegsspiel.play;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameManager {
    private Map<String, Player> playerList;
    private String namePlayerNow;
    private Set<String> playerNames;

    public GameManager() {
        playerList = new HashMap<>(2);
    }

    public void addPlayer(String name) {
        playerList.put(name, new Player());
    }

    public void startGame() {
        arrangeRegions();
        arrangeUnits();
        playerNames = playerList.keySet();
        namePlayerNow = playerNames.iterator().next();
        System.out.println(namePlayerNow);
        System.out.println(playerNames.iterator().next());
        /*
        TODO взять итератор и по нему ориентироваться
         */
    }

//    public void move(String playerName) {
//        if(namePlayNow.equals(playerName)){
//            /*
//            TODO sumething movement
//             */
//
//            namePlayNow = ;
//        }else{
//            System.out.println("Сейчас ход другого игрока");
//        }
//    }

    private void arrangeUnits(){
         /*
        TODO расставить войска
         */
    }

    private void arrangeRegions(){
          /*
        TODO расставить регионы
         */
    }
}
