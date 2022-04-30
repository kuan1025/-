package Recursion;

public class Maze {
    public static void main(String[] args) {
        // 用數組創建迷宮
        int[][] maze = new int[8][7];
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 7) {
                for (int j = 0; j < 7; j++) {
                    maze[i][j] = 1;
                }

            } else if (i == 3) {
                maze[i][0] = 1;
                maze[i][1] = 1;
                maze[i][2] = 1;
            } else {
                maze[i][0] = 1;
                maze[i][6] = 1;
            }

        }
        setWay(maze,1,1); 
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print("\t" + maze[i][j]);
                if (j == 6) {
                    System.out.println();
                }
            }
        }

    }

    /** 1. 走迷宮方法 如果找到就true
        2. i,j 為找路的起點
        3. maze[6][5] 是終點
        4. map[i][j] =  0 可以走
        5. map[i][j] =  1 不能走
        6. map[i][j] =  2 走過
        7. map[i][j] =  3 已經走過但是死路
     i = y j = x
     */
    public static boolean setWay(int[][] maze, int i, int j) {
        if(maze[6][5]==2) { //說明已經找到
            return true;
        }else {
            if (maze[i][j] == 0) {
                maze[i][j] = 2;
                if (setWay(maze, i + 1, j)) {
                    return true;
                } else if (setWay(maze, i, j + 1)) {
                    return true;
                } else if (setWay(maze, i - 1, j)) {
                    return true;
                } else if (setWay(maze, i, j - 1)) {
                    return true;
                } else {
                    maze[i][j]=3;
                    return false;
                }

            }else{
                   return false;
            }
        }
    }
}
