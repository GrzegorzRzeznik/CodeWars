package kyu5.Tic_Tac_Toe_3D;

import java.util.*;

/*
Regular Tic-Tac-Toe is boring.

That's why in this Kata you will be playing Tic-Tac-Toe in 3D using a 4 x 4 x 4 matrix!

https://i.imgur.com/NlG3wai.pngKata
Task
Play the game. Work out who wins.

Return a string

-O wins after <n> moves
-X wins after <n> moves
-No winner

Rules
Player O always goes first
Input moves is list/array/tuple of [x,y,z] (zero based)
Each player takes a turn until you find a winner, or there are no moves left
Four of the same symbols in a row wins
There may be more moves provided than are necessary to finish the game - that is for you to figure out
 */
public class Dinglemouse {

    public static String playOX3D(final List<int[]> moves) {

        if (moves.size() < 7) {
            return "No winner";
        }
        char[][][] board = new char[4][4][4];
        char[] players = new char[]{'O', 'X'};
        for (int i = 0; i < moves.size(); i++) {
            board[moves.get(i)[0]][moves.get(i)[1]][moves.get(i)[2]] = players[i % 2];
            if (i > 5 && gameOver(board, moves.get(i))) {
                return players[i % 2] + " wins after " + ++i + " moves";
            }
        }
        return "No winner";
    }

    private static boolean gameOver(char[][][] board, int[] coordinates) {
        char currentPlayer = board[coordinates[0]][coordinates[1]][coordinates[2]];
        return xLine(board, coordinates, currentPlayer)
                || yLine(board, coordinates, currentPlayer)
                || zLine(board, coordinates, currentPlayer)
                || diagonal(board, coordinates, currentPlayer);

    }

    private static boolean xLine(char[][][] board, int[] coordinates, char player) {
        for (int i = 0; i < 4; i++) {
            if (board[i][coordinates[1]][coordinates[2]] != player) return false;
        }
        return true;
    }

    private static boolean yLine(char[][][] board, int[] coordinates, char player) {
        for (int i = 0; i < 4; i++) {
            if (board[coordinates[0]][i][coordinates[2]] != player) return false;
        }
        return true;
    }

    private static boolean zLine(char[][][] board, int[] coordinates, char player) {
        for (int i = 0; i < 4; i++) {
            if (board[coordinates[0]][coordinates[1]][i] != player) return false;
        }
        return true;
    }

    private static boolean diagonal(char[][][] board, int[] coordinates, char player) {
        int[] diagLinesLength = new int[13];

        for (int i = 0; i < 4; i++) {
            if (board[i][i][coordinates[2]] == player) {
                diagLinesLength[0]++;
            }
            if (board[i][coordinates[1]][i] == player) {
                diagLinesLength[1]++;
            }
            if (board[coordinates[0]][i][i] == player) {
                diagLinesLength[2]++;
            }
            if (board[i][3 - i][coordinates[2]] == player) {
                diagLinesLength[3]++;
            }
            if (board[i][coordinates[1]][3 - i] == player) {
                diagLinesLength[4]++;
            }
            if (board[coordinates[0]][i][3 - i] == player) {
                diagLinesLength[5]++;
            }
            if (board[3 - i][i][coordinates[2]] == player) {
                diagLinesLength[6]++;
            }
            if (board[3 - i][coordinates[1]][i] == player) {
                diagLinesLength[7]++;
            }
            if (board[coordinates[0]][3 - i][i] == player) {
                diagLinesLength[8]++;
            }
            if (board[i][i][i] == player) {
                diagLinesLength[9]++;
            }
            if (board[3 - i][i][i] == player) {
                diagLinesLength[10]++;
            }
            if (board[i][i][3 - i] == player) {
                diagLinesLength[11]++;
            }
            if (board[3 - i][i][3 - i] == player) {
                diagLinesLength[12]++;
            }
        }

        int longestDiagonal = (int) Arrays.stream(diagLinesLength).max().orElse(0);

        return longestDiagonal == 4;
    }

}