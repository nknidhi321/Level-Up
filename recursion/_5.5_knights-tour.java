// https://www.pepcoding.com/resources/online-java-foundation/recursion-backtracking/knights-tour-official/ojquestion

// No attacking Scene like nQueen
// Knight moves in L shape, i.e.  2 & 1 Step | 1 & 2 Step

// Simple backtracking

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int startRow = sc.nextInt();
        int startCol = sc.nextInt();
        
        int[][] chess = new int[n][n];
        printKnightsTour(chess, startRow, startCol, 1);
    }

    public static int n;
    public static int[][] dir = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    
    public static void printKnightsTour(int[][] chess, int row, int col, int count) {
        if(count == n * n)  {
            chess[row][col] = count;
            displayBoard(chess);
            chess[row][col] = 0;
            return;
        }
        
        chess[row][col] = count;
        for(int d = 0; d < dir.length; d++) {
            int r = row + dir[d][0];
            int c = col + dir[d][1];
            
            if(r >= 0 && r < n && c >= 0 && c < n && chess[r][c] == 0) {
                printKnightsTour(chess, r, c, count + 1);
            }
        }
        chess[row][col] = 0;
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
