//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/nqueens-combinations-2das1d-queen-chooses-official/ojquestion

// Rajneesh 
// Radius way of writing IsQueenSafe
// Combination wala tareeka

import java.io.*;
import java.util.*;

public class Main {

    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        
		int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } }; // 4 Direction attacking
		int n = chess.length, m = chess[0].length;
	
		for(int d = 0; d < dir.length; d++) {
			for(int rad = 1; rad < chess.length; rad++) {
				int r = row + rad * dir[d][0];
				int c = col + rad * dir[d][1];
				if(r >= 0 && c >= 0 && r < n && c < m) {
					if(chess[r][c]) return false;
				} 
				else { 
					break; //If out of bounds in any one direction then stop checking for rest of the jump+1
				}
			}
		}
		return true;
	}


    // Levels pe queen rahegi, and options me aage ki boxes rahengi
    public static void nqueens(int qpsf, int tq, boolean[][] chess, int idx) {
    
        // All queens are placed, print the chess
        if (qpsf == tq) { 
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "q\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        // Queen k paas idx se leke last Box tak ka option h, kahi v baith jaao 
        for (int i = idx; i < chess.length * chess.length; i++) {
            int row = i / chess.length; // Decoding from 1D to 2D
            int col = i % chess.length; // Decoding from 1D to 2D

            if (chess[row][col] == false && IsQueenSafe(chess, row, col)) {
                chess[row][col] = true; // Mark 
                nqueens(qpsf + 1, tq, chess, i + 1); 
                chess[row][col] = false; // Backtrack
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];

        // Start placing queens from 1st Box
        nqueens(0, n, chess, 0);
    }
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Rajneesh
// Radius way of writing IsQueenSafe
// Subsequence wala tareeka

import java.io.*;
import java.util.*;

public class Main {

    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        
		int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } }; // 4 Direction attacking
		int n = chess.length, m = chess[0].length;
	
		for(int d = 0; d < dir.length; d++) {
			for(int rad = 1; rad < chess.length; rad++) {
				int r = row + rad * dir[d][0];
				int c = col + rad * dir[d][1];
				if(r >= 0 && c >= 0 && r < n && c < m) {
					if(chess[r][c]) return false;
				} 
				else { 
					break; //If out of bounds in any one direction then stop checking for rest of the jump+1
				}
			}
		}
		return true;
	}

    
    // Level pe queen and box dono aaegi
    public static void nqueen_01_combi_sub(int qpsf, int tnq, boolean[][] chess, int idx) {
        int n = chess.length, m = chess[0].length;
        
		if (qpsf == tnq || idx == n * m) {
			if (qpsf == tnq) {
    			for (int row = 0; row < chess.length; row++) {
                    for (int col = 0; col < chess.length; col++) {
                        System.out.print(chess[row][col] ? "q\t" : "-\t");
                    }
                    System.out.println();
                }
                System.out.println();
			}
            return;
		}

		int r = idx / m;
		int c = idx % m;
		
		//We are making calls to the very next box, hence No Row/Floor boundation
		//But implicitly Row/Floor boundation is there because of isSafeToPlaceQueen
		if (IsQueenSafe(chess, r, c)) { // You can skip box check, because this is combination, so box toh khali he hogi
			chess[r][c] = true;
			nqueen_01_combi_sub(qpsf + 1, tnq, chess, idx + 1); // Queen ki us box me aane ki choice
			chess[r][c] = false;
		}
        
        // Queen ki us box me nahi aane ki choice
		nqueen_01_combi_sub(qpsf, tnq, chess, idx + 1);
	}


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];

        // Start placing queens from 1st Box, qpsf => queen placed So far
        nqueen_01_combi_sub(0, n, chess, 0);
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Archit
// Same as 1st Combination Code except IsQueenSafe()
// Lengthy code for IsQueenSafe 

import java.io.*;
import java.util.*;

public class Main {

    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        // Checking top vertically
        for(int i = row - 1; i >= 0; i--) {
            if(chess[i][col]) {
                return false;
            }
        }
        
        // Checking left horizontally
        for(int j = col - 1; j >= 0; j--) {
            if(chess[row][j]) {
                return false;
            }
        }
        
        // Checking top diagonally
        for(int i = row - 1, j = col + 1; i >= 0 && j < chess[0].length; i--, j++) {
            if(chess[i][j]) {
                return false;
            }
        }
        
        // Checking top antiDiagonally
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(chess[i][j]) {
                return false;
            }
        }
        
        // If you do not fail at any above checks => You are safe
        return true;
    }


    // Levels pe queen rahegi, and options me aage ki boxes rahengi
    public static void nqueens(int qpsf, int tq, boolean[][] chess, int idx) {
    
        // All queens are placed, print the chess
        if (qpsf == tq) { 
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "q\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        // Queen k paas idx se leke last Box tak ka option h, kahi v baith jaao 
        for (int i = idx; i < chess.length * chess.length; i++) {
            int row = i / chess.length; // Decoding from 1D to 2D
            int col = i % chess.length; // Decoding from 1D to 2D

            if (chess[row][col] == false && IsQueenSafe(chess, row, col)) {
                chess[row][col] = true; // Mark 
                nqueens(qpsf + 1, tq, chess, i + 1); 
                chess[row][col] = false; // Backtrack
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];

        // Start placing queens from 1st Box
        nqueens(0, n, chess, 0);
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
