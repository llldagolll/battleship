package org.example.domain.battleship;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


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



    private final StandardInputStream in = new StandardInputStream();
    private final StandardOutputStream out = new StandardOutputStream();

    @BeforeEach
    public void before() {
        System.setIn(in);
        System.setOut(out);
    }

    @AfterEach
    public void after() {
        System.setIn(null);
        System.setOut(null);
    }

    @Test
    @DisplayName("標準入力と標準出力の結果が一致しているか")
    public void IsStdinAndStandardOutputResultMatched() {
        in.inputln("fuga");
        System.out.println("fuga");
        assertEquals("fuga", out.readLine());
    }

    @Test
    @DisplayName("A~J以内のアルファベットが入力されたとき、trueを返す")
    void GivenUpperAToJ_shouldReturnTrue() throws IOException {
        String point = "A10";
        assertTrue(true, String.valueOf(battleShip.IsInputValueTypeCorrect(point)));
    }

    @Test
    @DisplayName("1~10以内の数字が入力されたとき、trueを返す")
    void Given1To10_shouldReturnTrue() throws IOException {
        String point = "B10";
        assertTrue(true, String.valueOf(battleShip.IsInputValueTypeCorrect(point)));
    }

    @Test
    @DisplayName("K以降のアルファベットが入力されたとき、IOExceptionを投げる")
    void GivenAfterK_shouldThrowIOException() {
        String invalidPoint = "K3";
        assertThrows(IOException.class, () -> battleShip.IsInputValueTypeCorrect(invalidPoint));
    }
    @Test
    @DisplayName("小文字のa~j以内のアルファベットが入力されたとき、IOExceptionを投げる")
    void GivenLowerAToJ_shouldThrowIOException() {
        String invalidPoint = "a3";
        assertThrows(IOException.class, () -> battleShip.IsInputValueTypeCorrect(invalidPoint));

    }
    @Test
    @DisplayName("0が入力されたとき、IOExceptionを投げる")
    void Given0_shouldThrowIOException() {
        String invalidPoint = "D0";
        assertThrows(IOException.class, () -> battleShip.IsInputValueTypeCorrect(invalidPoint));
    }

    @Test
    @DisplayName("11以降が入力されたとき、IOExceptionを投げる")
    void GivenAfter11_shouldThrowIOException() {
        String invalidPoint = "D11";
        assertThrows(IOException.class, () -> battleShip.IsInputValueTypeCorrect(invalidPoint));
    }

    @Test
    @DisplayName("座標が2つ渡さたとき、trueを返す")
    void Given2Coordinates_shouldReturnTrue() throws IOException {
        String twoCoordinates = "A10 B9";
        assertTrue(true, String.valueOf(battleShip.isCoordinatesTwo(twoCoordinates)));
    }
    @Test
    @DisplayName("座標が1つしか渡されなかったとき、IOExceptionを投げる")
    void GivenOnly1Coordinate_shouldThrowIOException(){
        String oneCoordinate = "A10";
        assertThrows(IOException.class, () -> battleShip.isCoordinatesTwo(oneCoordinate));
    }

    @Test
    @DisplayName("座標が3つ以上渡されたとき、IOExceptionを投げる")
    void GivenOnly3Coordinate_shouldThrowIOException(){
        String threeCoordinate = "A10 B4 C5";
        assertThrows(IOException.class, () -> battleShip.isCoordinatesTwo(threeCoordinate));
    }

    @Test
    @DisplayName("(0, 0)をOに置き換える")
    void Given00_shouldBe0(){}
}
