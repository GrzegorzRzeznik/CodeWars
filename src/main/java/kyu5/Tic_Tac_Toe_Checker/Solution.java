package kyu5.Tic_Tac_Toe_Checker;
import java.util.Arrays;
/*
If we were to set up a Tic-Tac-Toe game, we would want to know whether the board's current state is solved, wouldn't we?
Our goal is to create a function that will check that for us!

Assume that the board comes in the form of a 3x3 array, where the value is 0 if a spot is empty, 1 if it is an "X", or 2 if it is an "O", like so:

[[0, 0, 1],
 [0, 1, 2],
 [2, 1, 0]]
We want our function to return:

-1 if the board is not yet finished AND no one has won yet (there are empty spots),
1 if "X" won,
2 if "O" won,
0 if it's a cat's game (i.e. a draw).
You may assume that the board passed in is valid in the context of a game of Tic-Tac-Toe.
 */

public class Solution {
    private static final int[] xWin = {1,1,1};
    private static final int[] yWin = {2,2,2};

    public static int isSolved(int[][] board) {
        int rowsCheck = checkRows(board);
        int columnsCheck = checkColumns(board);
        int diagonalsCheck = checkDiagonals(board);

        if(rowsCheck != 0){
            return checkRows(board);
        }
        if(columnsCheck != 0){
            return checkColumns(board);
        }
        if(diagonalsCheck != 0){
            return checkDiagonals(board);
        }
        return Arrays.stream(board).flatMapToInt(a -> Arrays.stream(a)).filter(x -> x == 0).count() > 0 ? -1 : 0;
    }
    private static int checkRows(int[][] board){

        for(int[] row : board){
            if(Arrays.equals(row, xWin)){
                return 1;
            }
            if(Arrays.equals(row, yWin)){
                return 2;
            }
        }
        return 0;
    }
    private static int checkColumns(int[][] board){

        for(int i = 0; i < board.length; i++){
            int[] vertical = {board[0][i], board[1][i], board[2][i]};
            if(Arrays.equals(xWin, vertical)){
                return 1;
            }
            if(Arrays.equals(yWin, vertical)){
                return 2;
            }
        }
        return 0;
    }
    private static int checkDiagonals(int[][] board){
        int[] diagonal1 = new int[]{board[0][0], board[1][1], board[2][2]};
        if(Arrays.equals(xWin, diagonal1)){
            return 1;
        }
        if(Arrays.equals(yWin, diagonal1)){
            return 2;
        }
        int[] diagonal2 = new int[]{board[2][0], board[1][1], board[0][2]};
        if(Arrays.equals(xWin, diagonal2)){
            return 1;
        }
        if(Arrays.equals(yWin, diagonal2)){
            return 2;
        }
        return 0;
    }
}