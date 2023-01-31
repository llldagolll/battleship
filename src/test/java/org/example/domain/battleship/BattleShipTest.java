package org.example.domain.battleship;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BattleShipTest {
    BattleShip battleShip = new BattleShip();

    @Test
    @DisplayName("二次元配列10 * 10が同じか")
    void A10_10_TwoDimensionalArray() {
        char[][] field = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }

        assertArrayEquals(field, battleShip.generateField());
    }


    @Test
    @DisplayName("大文字アルファベット A => Jの文字型の配列を作成")
    void IsAlphabetAWithJOfArray() {
        char[] rowOfAlphabets = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I', 'J'};
        assertArrayEquals(rowOfAlphabets, battleShip.getRowOfAlphabets());
    }
    @Test
    @DisplayName("1 => 10の文字型の配列を作成")
    void IsNumber1With10OfArray() {
        int[] columnOfNum = new int[]{1, 2, 3, 4, 5, 6, 7 ,8, 9, 10};
        assertArrayEquals(columnOfNum, battleShip.getColumnOfNum());
    }

}
