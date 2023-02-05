package org.example.domain.battleship;

import lombok.Data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
public class BattleShip {

    int row = 10;
    int column = 10;

    char[][] field = new char[row][column];
    String rowOfAlphabets = "ABCDEFGHIJ";

    int[] columnOfNum = new int[]{1, 2, 3, 4, 5, 6, 7 ,8, 9, 10};

    public BattleShip() {
        generateField();
    }

    public void run() throws InvalidInputFormatException {

        Ship[] ship = Ship.values();
        for (Ship s :
                ship) {
            play(s);
        }
    }

    void play(Ship ship) throws InvalidInputFormatException {
        Scanner scanner = new Scanner(System.in);

        try {
            showField();
            String c = input(scanner, ship);
            var coordinates = extractValidCoordinates(c);



            setShip(ship ,coordinates);

        } catch (InvalidInputFormatException | PlacedShipTooCloseToAnotherShipException | WrongLengthException e) {
            System.out.printf("%s\n", e.getMessage());
            System.out.println();
            play(ship);
        }
    }


    void showField() {
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
            System.out.print(rowOfAlphabets.charAt(i));
            System.out.print(' ');
            for (int j = 0; j < column; j++) {
                System.out.print(field[i][j]);
                System.out.print(' ');
            }
        }
        System.out.println();
    }

    String input(Scanner scanner, Ship ship) {
        printInputInterface(ship);
        String coordinates = scanner.nextLine();
        System.out.println();

        return coordinates;
    }

    boolean isCoordinateFormatCorrect(String point) throws InvalidInputFormatException {
        String regex = "[A-J]([1-9]|10)";
        Pattern p1 = Pattern.compile(regex); // 正規表現パターンの読み込み
        Matcher m1 = p1.matcher(point); // パターンと検査対象文字列の照合
        boolean result = m1.matches(); // 照合結果をtrueかfalseで取得
        if (!m1.matches()) {
            throw new InvalidInputFormatException();
        }
        return result;
    }


    public void setShip(Ship ship, Map<String, Map<String, Integer>> coordinates) throws InvalidInputFormatException, PlacedShipTooCloseToAnotherShipException, WrongLengthException {
        int startRow = coordinates.get("start").get("row");
        int startColumn = coordinates.get("start").get("column");
        int endRow = coordinates.get("end").get("row");
        int endColumn = coordinates.get("end").get("column");


        //船を横に置く場合
        if(startRow == endRow) {
            if (ship.getSize() != (endColumn-startColumn+1)) {
                throw  new WrongLengthException(ship.getName());
            }

            for (int i = 0; i < (endColumn - startColumn + 1); i++) {
                if (field[startRow][startColumn+i] == 'O') {
                    throw new PlacedShipTooCloseToAnotherShipException();
                }
            }

            for (int i = 0; i < (endColumn - startColumn + 1); i++) {
                field[startRow][startColumn+i] = 'O';
            }
            return;
        }

        //船を縦に置く場合
        if (ship.getSize() != (endRow - startRow + 1)) {
            throw  new WrongLengthException(ship.getName());
        }



        for (int i = 0; i < (endRow - startRow + 1); i++) {
            if (field[startRow+i][startColumn] == 'O') {
                throw new PlacedShipTooCloseToAnotherShipException();
            }
        }

        for (int i = 0; i < (endRow - startRow + 1); i++) {
            field[startRow+i][startColumn] = 'O';
        }
    }





     Map<String, Map<String, Integer>> extractValidCoordinates(String inputCoordinatesFromUser) throws InvalidInputFormatException {

        String[] c = split(inputCoordinatesFromUser);

        int startRow = rowOfAlphabets.indexOf(c[0].charAt(0));
        int endRow = rowOfAlphabets.indexOf(c[1].charAt(0));

        int startColumn;

        if (c[0].length() == 2) {
            startColumn = Integer.parseInt(c[0].substring(1,2)) -1;
        } else{
            startColumn = Integer.parseInt(c[0].substring(1, 3)) - 1;
        }

        int endColumn;

        if (c[1].length() == 2) {
            endColumn = Integer.parseInt(c[1].substring(1,2)) -1;
        } else {
            endColumn = Integer.parseInt(c[0].substring(1, 3)) - 1;
        }


         //船を斜めに置いたらエラーを投げる
         if (startRow != endRow && startColumn != endColumn) {
             throw new InvalidInputFormatException();
         }

         //船がボードからはみ出たらエラーを投げる
         if (endRow > 10 || endColumn > 10) {
             throw new InvalidInputFormatException();
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


    public String[] split(String inputCoordinatesFromUser) throws InvalidInputFormatException {
        String[] coordinates;
        coordinates = inputCoordinatesFromUser.split(" ");

        if (coordinates.length != 2){
            throw new InvalidInputFormatException();
        }

        for (String c :
                coordinates) {
            if(!isCoordinateFormatCorrect(c)){
                throw new InvalidInputFormatException();
            }
        }

        return coordinates;
    }
}
