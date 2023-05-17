package kyu6.Maze_Runner;

/*
Welcome Adventurer. Your aim is to navigate the maze and reach the finish point without touching any walls. Doing so will kill you instantly!
Task
You will be given a 2D array of the maze and an array of directions. Your task is to follow the directions given. If you reach the end point before all your moves have gone, you should return Finish. If you hit any walls or go outside the maze border, you should return Dead. If you find yourself still in the maze after using all the moves, you should return Lost.
The Maze array will look like

maze = [[1,1,1,1,1,1,1],
        [1,0,0,0,0,0,3],
        [1,0,1,0,1,0,1],
        [0,0,1,0,0,0,1],
        [1,0,1,0,1,0,1],
        [1,0,0,0,0,0,1],
        [1,2,1,0,1,0,1]]
..with the following key

      0 = Safe place to walk
      1 = Wall
      2 = Start Point
      3 = Finish Point
  direction = {"N","N","N","N","N","E","E","E","E","E"} == "Finish"
Rules
1. The Maze array will always be square i.e. N x N but its size and content will alter from test to test.

2. The start and finish positions will change for the final tests.

3. The directions array will always be in upper case and will be in the format of N = North, E = East, W = West and S = South.

4. If you reach the end point before all your moves have gone, you should return Finish.

5. If you hit any walls or go outside the maze border, you should return Dead.

6. If you find yourself still in the maze after using all the moves, you should return Lost.
 */
public class MazeRunner {
    public static String walk(int[][] maze, String[] directions) {

        int x = 0;
        int y = 0;
        String position = "";
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++){
                if( maze[i][j] == 2){
                    x = j;
                    y = i;
                    break;
                }
            }
        }

        for(String s : directions){
            switch(s){
                case "N":
                    y--;
                    position = currentPosition(maze, x, y);
                    break;
                case "S":
                    y++;
                    position = currentPosition(maze, x, y);
                    break;
                case "W":
                    x--;
                    position = currentPosition(maze, x, y);
                    break;
                case "E":
                    x++;
                    position = currentPosition(maze, x, y);
                    break;
            }
            if(position.equals("Dead")){
                return position;
            }
            if(position.equals("Finish")){
                return position;
            }
        }

        return position;
    }

    private static String currentPosition(int[][] maze, int x, int y){
        int position;
        try{
            position = maze[y][x];
        }catch(IndexOutOfBoundsException e){
            return "Dead";
        }
        if(position == 1){
            return "Dead";
        }
        if(position == 3){
            return "Finish";
        }
        return "Lost";

    }
}