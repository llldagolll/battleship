package org.example.domain.battleship;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class BattleShipTest {

    private BattleShip battleShip;

    @BeforeEach
    void setUp() {
        this.battleShip = new BattleShip();
    }

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





    @Test
    @DisplayName("A~J以内のアルファベットが入力されたとき、trueを返す")
    void givenUpperAToJ_shouldReturnTrue() throws InvalidInputFormatException {
        String point = "A10";
        assertTrue(true, String.valueOf(battleShip.isCoordinateFormatCorrect(point)));
    }

    @Test
    @DisplayName("1~10以内の数字が入力されたとき、trueを返す")
    void given1To10_shouldReturnTrue() throws InvalidInputFormatException {
        String point = "B10";
        assertTrue(true, String.valueOf(battleShip.isCoordinateFormatCorrect(point)));
    }

    @Test
    @DisplayName("K以降のアルファベットが入力されたとき、InvalidInputFormatExceptionを投げる")
    void givenAfterK_shouldThrowInvalidInputFormatException() {
        String invalidPoint = "K3";
        assertThrows(InvalidInputFormatException.class, () -> battleShip.isCoordinateFormatCorrect(invalidPoint));
    }
    @Test
    @DisplayName("小文字のa~j以内のアルファベットが入力されたとき、InvalidInputFormatExceptionを投げる")
    void givenLowerAToJ_shouldThrowInvalidInputFormatException() {
        String invalidPoint = "a3";
        assertThrows(InvalidInputFormatException.class, () -> battleShip.isCoordinateFormatCorrect(invalidPoint));

    }
    @Test
    @DisplayName("0が入力されたとき、InvalidInputFormatExceptionを投げる")
    void given0_shouldThrowInvalidInputFormatException() {
        String invalidPoint = "D0";
        assertThrows(InvalidInputFormatException.class, () -> battleShip.isCoordinateFormatCorrect(invalidPoint));
    }

    @Test
    @DisplayName("11以降が入力されたとき、InvalidInputFormatExceptionを投げる")
    void givenAfter11_shouldThrowInvalidInputFormatException() {
        String invalidPoint = "D11";
        assertThrows(InvalidInputFormatException.class, () -> battleShip.isCoordinateFormatCorrect(invalidPoint));
    }

    @Test
    @DisplayName("座標が2つ渡さたとき、trueを返す")
    void given2Coordinates_shouldReturnTrue() throws InvalidInputFormatException {
        String twoCoordinates = "A10 B9";
        assertTrue(true, Arrays.toString(battleShip.split(twoCoordinates)));
    }
    @Test
    @DisplayName("座標が1つしか渡されなかったとき、InvalidInputFormatExceptionを投げる")
    void givenOnly1Coordinate_shouldThrowInvalidInputFormatException(){
        String oneCoordinate = "A10";
        assertThrows(InvalidInputFormatException.class, () -> battleShip.split(oneCoordinate));
    }

    @Test
    @DisplayName("座標が3つ以上渡されたとき、InvalidInputFormatExceptionを投げる")
    void givenOnly3Coordinate_shouldThrowInvalidInputFormatException(){
        String threeCoordinate = "A10 B4 C5";
        assertThrows(InvalidInputFormatException.class, () -> battleShip.split(threeCoordinate));
    }

    @Test
    @DisplayName("座標は正しく取れているか？")
    void givenA1_shouldBe00() throws InvalidInputFormatException {
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
    @DisplayName("船を横に置いた場合(座標A1からA5をOに置き換える)")
    void givenA1ToA5_shouldBeO() throws InvalidInputFormatException, PlacedShipTooCloseToAnotherShipException, WrongLengthException {
        char[][] field = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }

        field[0][0] = 'O';
        field[0][1] = 'O';
        field[0][2] = 'O';
        field[0][3] = 'O';
        field[0][4] = 'O';
        var coordinates = extractCoordinates("A1 A5");
        battleShip.setShip(Ship.AIRCRAFT_CARRIER ,coordinates);
        assertArrayEquals(field, battleShip.getField());
    }

    @Test
    @DisplayName("船を縦に置いた場合(座標B1からF1をOに置き換える)")
    void givenA1ToE1_shouldBeO() throws InvalidInputFormatException, PlacedShipTooCloseToAnotherShipException, WrongLengthException {
        char[][] field = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }

        field[1][0] = 'O';
        field[2][0] = 'O';
        field[3][0] = 'O';
        field[4][0] = 'O';
        field[5][0] = 'O';

        Map<String, Map<String, Integer>> coordinates = extractCoordinates("B1 F1");

        battleShip.setShip(Ship.AIRCRAFT_CARRIER, coordinates);
        assertArrayEquals(field, battleShip.getField());
    }

    @Test
    @DisplayName("船を斜めに置いたらInvalidInputFormatExceptionを投げる")
    void whenPutShipOnCross_ThrowInvalidInputFormatException()  {
        assertThrows(InvalidInputFormatException.class, () -> battleShip.extractCoordinates("A1 B5"));
    }

    @Test
    @DisplayName("船がフィールドからはみ出たらInvalidInputFormatExceptionを投げる")
    void whenShipFieldWithOut_ThrowInvalidInputFormatException() {
        assertThrows(InvalidInputFormatException.class, () -> battleShip.extractCoordinates("A7 A11"));
    }

    @Test
    @DisplayName("船のサイズと入力された座標のサイズが違うときWrongLengthExceptionを投げる、船のサイズより小さかった場合")
    void whenShipSizeAndInputCoordinateSizeDifferentSmaller_ThrowInvalidInputFormatException() {
        var coordinates = extractCoordinates("A1 A3");
        assertThrows(WrongLengthException.class, () -> battleShip.setShip(Ship.AIRCRAFT_CARRIER, coordinates));
    }


    @Test
    @DisplayName("船のサイズと入力された座標のサイズが違うときWrongLengthExceptionを投げる、船のサイズよりも大きかった場合")
    void whenShipSizeAndInputCoordinateSizeDifferentBigger_ThrowInvalidInputFormatException()  {
        var coordinates = extractCoordinates("A1 A6");
        assertThrows(WrongLengthException.class, () -> battleShip.setShip(Ship.AIRCRAFT_CARRIER, coordinates));
    }

    @Test
    @DisplayName("すでに船が配置されているところに配置しようとしたとき、PlacedShipTooCloseToAnotherShipExceptionを投げる")
    void whenAlreadyShipPlaced_shouldThrowInvalidInputFormatException() throws PlacedShipTooCloseToAnotherShipException, InvalidInputFormatException, WrongLengthException {
        var coordinates = extractCoordinates("A1 A5");
        battleShip.setShip(Ship.AIRCRAFT_CARRIER, coordinates);
        assertThrows(PlacedShipTooCloseToAnotherShipException.class, () -> battleShip.setShip(Ship.AIRCRAFT_CARRIER ,coordinates));
    }



     Map<String, Map<String, Integer>> extractCoordinates(String inputCoordinatesFromUser) {

        String[] c = inputCoordinatesFromUser.split(" ");

        int startRow = battleShip.rowOfAlphabets.indexOf(c[0].charAt(0));
        int startColumn;

        if (c[0].length() == 2) {
            startColumn = Integer.parseInt(c[0].substring(1,2)) -1;
        } else{
            startColumn = Integer.parseInt(c[0].substring(1, 3)) - 1;
        }

        int endRow = battleShip.rowOfAlphabets.indexOf(c[1].charAt(0));
        int endColumn;

        if (c[1].length() == 2) {
            endColumn = Integer.parseInt(c[1].substring(1,2)) -1;
        } else {
            endColumn = Integer.parseInt(c[0].substring(1, 3)) - 1;
        }


        Map<String, Map<String, Integer>> coordinates = new HashMap<>();
        coordinates.put("start", new HashMap<>());
        coordinates.put("end", new HashMap<>());

        coordinates.get("start").put("row", startRow);
        coordinates.get("start").put("column", startColumn);
        coordinates.get("end").put("row", endRow);
        coordinates.get("end").put("column", endColumn);


        return coordinates;
    }



}
