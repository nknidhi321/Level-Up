// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/nqueens-permutations-2das1d-official-queen-chooses/ojquestion
// Permuation hai, so 8 direction se attack ho sakta h, so check all the directions


// Combination

import java.io.*;
import java.util.*;

public class Main {

    public static boolean IsQueenSafe(String[][] chess, int row, int col) {
        
		int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } }; // 8 Direction attacking
		int n = chess.length, m = chess[0].length;
	
		for(int d = 0; d < dir.length; d++) {
			for(int rad = 1; rad < chess.length; rad++) {
				int r = row + rad * dir[d][0];
				int c = col + rad * dir[d][1];
				if(r >= 0 && c >= 0 && r < n && c < m) {
					if(!chess[r][c].equals("-")) {
					    return false;
					}
				} 
				else { 
					break; //If out of bounds in any one direction then stop checking for rest of the jump+1
				}
			}
		}
		return true;
	}

    
    // Level pe queen aaegi
    public static void nqueen_01_permu_sub(int qpsf, int tnq, int count, String[][] chess) {
      
        int n = chess.length, m = chess[0].length;
        
		if (qpsf == tnq) { // Print
			for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] + "\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
		}

		// Har queen ko n * m options poocha jaaega baithne k liye 
        for(int i = 0; i < n * m; i++) {
    		int r = i / m;
    		int c = i % m;
    		
    		//We are again making calls to (0, 0) hence No Row/Floor boundation
    		//But implicitly Row/Floor boundation is there because of isSafeToPlaceQueen
    		if (chess[r][c].equals("-") && IsQueenSafe(chess, r, c)) { 
    			chess[r][c] = "q" + count;
    			nqueen_01_permu_sub(qpsf + 1, tnq, count +  1, chess); // options har baar 0 se n * m tak he poocha jaaega, so idx pass krne ki zaroorat nahi h
    			chess[r][c] = "-";
    		}
        }
	}
	

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] chess = new String[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                chess[i][j] = "-";
            }
        }
        nqueen_01_permu_sub(0, n, 1, chess);
    }
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Subsequence

import java.io.*;
import java.util.*;

public class Main {

    public static boolean IsQueenSafe(String[][] chess, int row, int col) {
        
		int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } }; // 8 Direction attacking
		int n = chess.length, m = chess[0].length;
	
		for(int d = 0; d < dir.length; d++) {
			for(int rad = 1; rad < chess.length; rad++) {
				int r = row + rad * dir[d][0];
				int c = col + rad * dir[d][1];
				if(r >= 0 && c >= 0 && r < n && c < m) {
					if(!chess[r][c].equals("-")) {
					    return false;
					}
				} 
				else { 
					break; //If out of bounds in any one direction then stop checking for rest of the jump+1
				}
			}
		}
		return true;
	}

    
    // Level pe queen and box dono aaegi
    public static void nqueen_01_permu_sub(int qpsf, int tnq, int count, String[][] chess, int idx) {
        int n = chess.length, m = chess[0].length;
        
		if (qpsf == tnq || idx == n * m) {
			if (qpsf == tnq) { // Print
    			for (int row = 0; row < chess.length; row++) {
                    for (int col = 0; col < chess.length; col++) {
                        System.out.print(chess[row][col] + "\t");
                    }
                    System.out.println();
                }
                System.out.println();
			}
            return;
		}

		int r = idx / m;
		int c = idx % m;
		
		//We are again making calls to (0, 0) hence No Row/Floor boundation
		//But implicitly Row/Floor boundation is there because of isSafeToPlaceQueen
		if (chess[r][c].equals("-") && IsQueenSafe(chess, r, c)) { 
			chess[r][c] = "q" + count; // Visited
			nqueen_01_permu_sub(qpsf + 1, tnq, count +  1, chess, 0); // Queen ki us box me aane ki choice // Agar aaegi toh firse 0 se call legegi
			chess[r][c] = "-"; // Backtrack
		}
        
        // Queen ki us box me nahi aane ki choice //We are making calls to the next box => idx + 1
		nqueen_01_permu_sub(qpsf, tnq, count, chess, idx + 1); // Agar nahi aaegi toh agle box k liye poocho
	}
	

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] chess = new String[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                chess[i][j] = "-";
            }
        }
        nqueen_01_permu_sub(0, n, 1, chess, 0);
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
