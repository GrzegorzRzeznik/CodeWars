package kyu5.Bird_Mountain;

/*
Kata Task
A bird flying high above a mountain range is able to estimate the height of the highest peak.

Can you?

Example
The birds-eye view
^^^^^^
 ^^^^^^^^
  ^^^^^^^
  ^^^^^
  ^^^^^^^^^^^
  ^^^^^^
  ^^^^
The bird-brain calculations

111111
 1^^^^111
  1^^^^11
  1^^^1
  1^^^^111111
  1^^^11
  1111

111111
 12222111
  12^^211
  12^21
  12^^2111111
  122211
  1111

111111
 12222111
  1233211
  12321
  12332111111
  122211
  1111

Height = 3
 */
public class Dinglemouse {
    private static byte currentHeight;
    private static char previousHeight;

    public static int peakHeight(char[][] mountain) {
        currentHeight = 1;
        previousHeight = ' ';
        boolean con;
        do {
            con = false;
            for (int i = 0; i < mountain.length; i++) {
                for (int j = 0; j < mountain[i].length; j++) {
                    if (mountain[i][j] == '^') {
                        if (isOnEdge(mountain, i, j)) {
                            mountain[i][j] = (char) (currentHeight + '0');
                            con = true;
                        }
                    }
                }
            }
            previousHeight = (char) (currentHeight + '0');
            currentHeight++;
        } while (con);


        return currentHeight - 2;
    }

    private static boolean isOnEdge(char[][] m, int r, int c) {
        if (r == 0 || c == 0 || r == m.length - 1 || c == m[r].length - 1) {
            return true;
        } else
            return m[r][c - 1] == previousHeight
                    || m[r][c + 1] == previousHeight
                    || m[r - 1][c] == previousHeight
                    || m[r + 1][c] == previousHeight;
    }
}