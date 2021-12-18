// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/nknights-combinations-2das1d-knight-chooses-official/ojquestion

// Same as Queen but Knight kills at 2 & 1 | 1 & 2 
// Make sure there's no attacking knight and print all the possible configurations.

import java.io.*;
import java.util.*;

public class Main {

	// Khud se upar wali rows k 4 attcking positions pe check karna kaafi hai,
	// Kuki niche hum aaj tak gaye he nahi so waha knight placed hone ki probability 0 hai
    public static boolean IsKnightSafe(boolean[][] chess, int r, int c) {
        if(r - 2 >= 0 && c - 1 >= 0 && chess[r - 2][c - 1]) return false;
        if(r - 1 >= 0 && c - 2 >= 0 && chess[r - 1][c - 2]) return false;
        if(r - 2 >= 0 && c + 1 < n && chess[r - 2][c + 1]) return false;
        if(r - 1 >= 0 && c + 2 < n && chess[r - 1][c + 2]) return false;
        return true;
    }

	// Knight level pe hogi
    public static void nknights(int kpsf, int tk, boolean[][] chess, int idx) {
        if (kpsf == tk) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "k\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = idx; i < chess.length * chess.length; i++) { // Knight ko idx se n * n tak sare options poocho
            int row = i / chess.length;
            int col = i % chess.length;

            if (IsKnightSafe(chess, row, col)) { // Combination hai, so no need to check that very cell
                chess[row][col] = true;
                nknights(kpsf + 1, tk, chess, i + 1);
                chess[row][col] = false;
            }
        }
    }

    public static int n;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];

        nknights(0, n, chess, 0);
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
