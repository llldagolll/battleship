package org.example;

import org.example.domain.battleship.BattleShip;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BattleShip battleShip = new BattleShip();
        battleShip.run();
    }
}