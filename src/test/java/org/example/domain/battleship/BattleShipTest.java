package org.example.domain.battleship;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        String rowOfAlphabets = "ABCDEFGHIJ";
        assertEquals(rowOfAlphabets, battleShip.getRowOfAlphabets());
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
        assertTrue(true, String.valueOf(battleShip.isCoordinateFormatCorrect(point)));
    }

    @Test
    @DisplayName("1~10以内の数字が入力されたとき、trueを返す")
    void Given1To10_shouldReturnTrue() throws IOException {
        String point = "B10";
        assertTrue(true, String.valueOf(battleShip.isCoordinateFormatCorrect(point)));
    }

    @Test
    @DisplayName("K以降のアルファベットが入力されたとき、IOExceptionを投げる")
    void GivenAfterK_shouldThrowIOException() {
        String invalidPoint = "K3";
        assertThrows(IOException.class, () -> battleShip.isCoordinateFormatCorrect(invalidPoint));
    }
    @Test
    @DisplayName("小文字のa~j以内のアルファベットが入力されたとき、IOExceptionを投げる")
    void GivenLowerAToJ_shouldThrowIOException() {
        String invalidPoint = "a3";
        assertThrows(IOException.class, () -> battleShip.isCoordinateFormatCorrect(invalidPoint));

    }
    @Test
    @DisplayName("0が入力されたとき、IOExceptionを投げる")
    void Given0_shouldThrowIOException() {
        String invalidPoint = "D0";
        assertThrows(IOException.class, () -> battleShip.isCoordinateFormatCorrect(invalidPoint));
    }

    @Test
    @DisplayName("11以降が入力されたとき、IOExceptionを投げる")
    void GivenAfter11_shouldThrowIOException() {
        String invalidPoint = "D11";
        assertThrows(IOException.class, () -> battleShip.isCoordinateFormatCorrect(invalidPoint));
    }

    @Test
    @DisplayName("座標が2つ渡さたとき、trueを返す")
    void Given2Coordinates_shouldReturnTrue() throws IOException {
        String twoCoordinates = "A10 B9";
        assertTrue(true, String.valueOf(battleShip.split(twoCoordinates)));
    }
    @Test
    @DisplayName("座標が1つしか渡されなかったとき、IOExceptionを投げる")
    void GivenOnly1Coordinate_shouldThrowIOException(){
        String oneCoordinate = "A10";
        assertThrows(IOException.class, () -> battleShip.split(oneCoordinate));
    }

    @Test
    @DisplayName("座標が3つ以上渡されたとき、IOExceptionを投げる")
    void GivenOnly3Coordinate_shouldThrowIOException(){
        String threeCoordinate = "A10 B4 C5";
        assertThrows(IOException.class, () -> battleShip.split(threeCoordinate));
    }

    @Test
    @DisplayName("座標は正しく取れているか？")
    void GivenA1_shouldBe00() throws IOException {
        Map<String, Map<String, Integer>> expected = new HashMap<>();

        expected.put("start", new HashMap<>());
        expected.put("end", new HashMap<>());

        expected.get("start").put("row", 0);
        expected.get("start").put("column", 0);
        expected.get("end").put("row", 0);
        expected.get("end").put("column", 4);

        Map<String, Map<String, Integer>> actual = battleShip.extractCoordinates("A1 A5");

        assertEquals(expected.get("start").get("row"), actual.get("start").get("row"));
        assertEquals(expected.get("start").get("column"), actual.get("start").get("column"));
        assertEquals(expected.get("end").get("row"), actual.get("end").get("row"));
        assertEquals(expected.get("end").get("column"), actual.get("end").get("column"));
    }

    @Test
    @DisplayName("座標A1をOに置き換える")
    void GivenA1_shouldBeO() throws IOException {
        char[][] field = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }
        field[0][1] = 'O';
        battleShip.generateField();
        battleShip.placeO("A1");
        assertArrayEquals(field, battleShip.getField());
    }


    @Test
    @DisplayName("座標A1からA5をOに置き換える")
    void GivenA1ToA5_shouldBeO() throws IOException {
        char[][] field = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }
        for (int i = 0; i < 5; i++) {
            field[0][i] = 'O';
        }

        battleShip.generateField();
        battleShip.setShip("A1 A5");

        assertArrayEquals(field, battleShip.getField());

    }
}
