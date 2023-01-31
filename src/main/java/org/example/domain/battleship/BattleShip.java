package org.example.domain.battleship;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@NoArgsConstructor
@Data
public class BattleShip {
    int row = 10;
    int column = 10;

    char[][] field = new char[row][column];
    char[] rowOfAlphabets = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I', 'J'};

    int[] columnOfNum = new int[]{1, 2, 3, 4, 5, 6, 7 ,8, 9, 10};


    public void play() {
        Ship[] ship = Ship.values();
        Scanner scanner = new Scanner(System.in);

        for (Ship s :
                ship) {
            showField();
            String coordinates = inputCoordinatesFromUser(scanner, s);
//            setShip(coordinates);
        }
    }

//    private void setShip(String input) {
//
//    }



    void showField() {
        generateField();
        int row = getRow();
        int column = getColumn();
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                System.out.print(' ');
                System.out.print(' ');
                for (int c:
                        columnOfNum) {
                    System.out.print(c);
                    System.out.print(' ');
                }
            }
            System.out.println();
            System.out.print(rowOfAlphabets[i]);
            System.out.print(' ');
            for (int j = 0; j < column; j++) {
                System.out.print(field[i][j]);
                System.out.print(' ');
            }
        }
        System.out.println();
    }

    String inputCoordinatesFromUser(Scanner scanner, Ship ship) {
        printInputInterface(ship);
        String coordinates = scanner.nextLine();
        System.out.println(coordinates); //debug用
        System.out.println();

        return coordinates;
    }

    boolean IsInputValueTypeCorrect(String point) throws IOException {
        String regex = "[A-J]([1-9]|10)";
        Pattern p1 = Pattern.compile(regex); // 正規表現パターンの読み込み
        Matcher m1 = p1.matcher(point); // パターンと検査対象文字列の照合
        boolean result = m1.matches(); // 照合結果をtrueかfalseで取得
        if (!m1.matches()) {
            throw new IOException("不適切なフォーマットです");
        }
        return result;
    }

    boolean isCoordinatesTwo(String validInput) throws IOException {
        if (validInput.split(" ").length != 2) {
            throw new IOException();
        }
        return true;
    }

    private void printInputInterface(Ship ship) {
        System.out.println();
        System.out.printf("Enter the coordinates of %s (%d cells):", ship.getName(), ship.getSize());
        System.out.println();
        System.out.println();
        System.out.print("> ");
    }


     char[][] generateField() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }

       return field;
    }


}
