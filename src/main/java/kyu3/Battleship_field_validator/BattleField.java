package kyu3.Battleship_field_validator;
import java.util.HashMap;
import java.util.Map;
/*
Write a method that takes a field for well-known board game "Battleship" as an argument and returns true if it has a valid disposition of ships,
false otherwise. Argument is guaranteed to be 10*10 two-dimension array. Elements in the array are numbers, 0 if the cell is free and 1 if occupied by ship.

Battleship (also Battleships or Sea Battle) is a guessing game for two players. Each player has a 10x10 grid containing several
"ships" and objective is to destroy enemy's forces by targetting individual cells on his field. The ship occupies one or more cells in the grid.
Size and number of ships may differ from version to version. In this kata we will use Soviet/Russian version of the game.
https://i.imgur.com/IWxeRBV.png

Before the game begins, players set up the board and place the ships accordingly to the following rules:
There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers (size 2) and 4 submarines (size 1).
Any additional ships are not allowed, as well as missing ships.
Each ship must be a straight line, except for submarines, which are just single cell.
https://i.imgur.com/FleBpT9.png

The ship cannot overlap or be in contact with any other ship, neither by edge nor by corner.
https://i.imgur.com/MuLvnug.png

 */
public class BattleField {

    private static int[][] board = new int[12][12];
    private static Map<Integer, Integer> ships = new HashMap<>();

    public static boolean fieldValidator(int[][] field) {
        board = new int[12][12];
        ships = new HashMap<>();
        if (!validateTotalFieldsTaken(field)) {
            return false;
        }
        rewriteToBiggerBoard(field);
        countAndEraseSubmarines();
        countAndEraseShips();
        if(ships.size() != 4 || !ships.containsKey(1) && !ships.containsKey(2) || !ships.containsKey(3) || !ships.containsKey(4)){
            return false;
        }
        return ships.get(1) == 4 && ships.get(2) == 3 && ships.get(3) == 2 && ships.get(4) == 1;
    }
    private static void rewriteToBiggerBoard(int[][] field) {
        for (int i = 0; i < 10; i++) {
            System.arraycopy(field[i], 0, board[i + 1], 1, 10);
        }
    }
    private static void countAndEraseSubmarines() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    if (board[i - 1][j] == 0
                            && board[i][j - 1] == 0
                            && board[i + 1][j] == 0
                            && board[i][j + 1] == 0
                            && board[i - 1][j - 1] == 0
                            && board[i - 1][j + 1] == 0
                            && board[i + 1][j - 1] == 0
                            && board[i + 1][j + 1] == 0) {
                        addShip(1);
                        board[i][j] = 0;
                    }
                }
            }
        }
    }
    private static void countAndEraseShips() {
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board.length - 1; j++) {
                if (board[i][j] == 1) {
                    int length = findLength(i, j);
                    addShip(length);
                    eraseShip(i, j, length);
                }
            }
        }
    }
    private static int findLength(int x, int y) {
        int length = 1;
        boolean horizontal = board[x][y + 1] == 1;
        if (horizontal) {
            for (int i = y + 1; i < board.length; i++) {
                if (board[x][i] == 0) {
                    return length;
                }
                length++;
            }
        } else {
            for (int i = x + 1; i < board.length; i++) {
                if (board[i][y] == 0) {
                    return length;
                }
                length++;
            }
        }
        return length;
    }
    private static void eraseShip(int x, int y, int length) {
        boolean horizontal = board[x][y + 1] == 1;
        int[][] emptyField;

        if (horizontal) {
            emptyField = new int[3][length + 2];
        } else {
            emptyField = new int[length + 2][3];
        }
        for (int i = 0; i < emptyField.length; i++) {
            System.arraycopy(emptyField[i], 0, board[x - 1 + i], y - 1, emptyField[i].length);
        }
    }
    private static void addShip(int length) {
        if (ships.containsKey(length)) {
            ships.replace(length, ships.get(length) + 1);
        } else {
            ships.put(length, 1);
        }
    }
    private static boolean validateTotalFieldsTaken(int[][] field) {
        int counter = 0;
        for (int[] row : field) {
            for (int i : row) {
                if (i == 1) {
                    counter++;
                }
            }
        }
        return counter == 20;
    }
}