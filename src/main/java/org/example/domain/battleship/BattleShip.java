package org.example.domain.battleship;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Scanner;

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
            String input = inputFromUser(scanner, s);
            setShip(input);
        }
    }


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

    String inputFromUser(Scanner scanner, Ship ship) {
        System.out.println();
        System.out.printf("Enter the coordinates of %s (%d cells):", ship.getName(), ship.getSize());
        System.out.println();
        System.out.println();
        System.out.print("> ");
        String input = scanner.nextLine();
        System.out.println(input); //debug用
        System.out.println();

        return input;
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
