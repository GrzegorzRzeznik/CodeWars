package kyu4.Snail;
/*
Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.

array = [[1,2,3],
         [4,5,6],
         [7,8,9]]
snail(array) #=> [1,2,3,6,9,8,7,4,5]
For better understanding, please follow the numbers of the next array consecutively:

array = [[1,2,3],
         [8,9,4],
         [7,6,5]]
snail(array) #=> [1,2,3,4,5,6,7,8,9]
This image will illustrate things more clearly:
https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcREpPz_94NBqtd2B7w412K36r2uSCrf8xudNQCfffSS2XHhLnJT

NOTE: The idea is not sort the elements from the lowest value to the highest; the idea is to traverse the 2-d array in a clockwise snailshell pattern.

NOTE 2: The 0x0 (empty matrix) is represented as en empty array inside an array [[]].
 */

public class Snail {

    public static int[] snail(int[][] array) {

        int startCol = 0;
        int endCol = array[0].length - 1;
        int startRow = 0;
        int endRow = array.length - 1;
        int[] result = new int[array.length * array[0].length];
        int index = 0;
        while(startCol <= endCol && startRow <= endRow){
            for(int i = startCol; i <= endCol; i++){
                result[index++] = array[startRow][i];
            }
            startRow++;
            for(int j = startRow; j <= endRow; j++){
                result[index++] = array[j][endCol];
            }
            endCol--;
            for(int k = endCol; k >= startCol; k-- ){
                result[index++] = array[endRow][k];
            }
            endRow--;
            for(int l = endRow; l >= startRow; l--){
                result[index++] = array[l][startCol];
            }
            startCol++;
        }
        return result;
    }
}