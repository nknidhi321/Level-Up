// tnb : total no of boxes, tnq : total no queens

package recursion;

public class _4_NQueens {
	
	//NO ATTACK, NO ROW/FLOOOR BOUNDATION====================================================================================

	//No Attacks, No Row/Floor boundation, 1D Grid
	public static int queenCombination1D(int tnb, int tnq, int bno, int qno, String ans) {
		if (qno == tnq) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = bno; i < tnb; i++) {

			//Note: Marking is not important because we make next call at i + 1,
			//So, there's no chance for 1+ queen to sit in the same box
			count += queenCombination1D(tnb, tnq, i + 1, qno + 1, ans + "b" + i + "q" + qno + " ");
		}
		return count;
	}

	//No Attacks, No Row/Floor boundation, 1D Grid
	public static int queenPermutation1D(boolean[] boxes, int tnq, int bno, int qno, String ans) {
		if (qno == tnq) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = bno; i < boxes.length; i++) {
			if (!boxes[i]) {
				boxes[i] = true; //Mark //We do not want 1+ queens to sit in the same box
				//Marking is important, because we are making calls again from 0
				count += queenPermutation1D(boxes, tnq, 0, qno + 1, ans + "b" + i + "q" + qno + " ");
				boxes[i] = false; //Unmark
			}
		}
		return count;
	}
	
	///No Attacks, No Row/Floor boundation, 2D Grid
	public static int queenCombination2D(boolean[][] board, int tnq, int bno, String ans) {
		if (tnq == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0, n = board.length, m = board[0].length;
		for (int i = bno; i < n * m; i++) {
			int r = i / m;
			int c = i % m;
			
			//Note: Marking is not important because we make next call at i + 1,
			//So, there's no chance for 1+ queen to sit in the same box
			count += queenCombination2D(board, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
		}

		return count;
	}

	//No Attacks, No Row/Floor boundation, 2D Grid
	public static int queenPermutation2D(boolean[][] board, int tnq, int bno, String ans) {
		if (tnq == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0, n = board.length, m = board[0].length;
		for (int i = bno; i < n * m; i++) {
			int r = i / m;
			int c = i % m;
			if (!board[r][c]) {
				board[r][c] = true; //Mark //We do not want 1+ queens to sit in the same box
				//Marking is important, because we are making calls again from 0
				count += queenPermutation2D(board, tnq - 1, 0, ans + "(" + r + "," + c + ") ");
				board[r][c] = false; //Unmark
			}
		}

		return count;
	}

	
	//====================================================================================================================================
	
	//ATTACKS, NO ROW/FLOOOR BOUNDATION====================================================================================
	
	
	//Attacks in all Directions
	public static boolean isSafeToPlaceQueen(boolean[][] board, int row, int col) {
		//int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } }; // 4 Direction attacking
		int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } }; //8 Direction attacking
		int n = board.length, m = board[0].length;
	
		for(int d = 0; d < dir.length; d++) {
			for(int rad = 1; rad < board.length; rad++) {
				int r = row + rad * dir[d][0];
				int c = col + rad * dir[d][1];
				if(r >= 0 && c >= 0 && r < n && c < m) {
					if(board[r][c]) return false;
				} 
				else { 
					break; //If out of bounds in any one direction then stop checking for rest of the jump+1
				}
			}
		}
		return true;
	}

	
	//Attacks, No Row/Floor boundation, 2D Grid (Combination)===================================================================
	public static int nqueen_01_combi(boolean[][] board, int tnq, int idx, String ans) {
		if (tnq == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0, n = board.length, m = board[0].length;
		for (int i = idx; i < n * m; i++) {
			int r = i / m;
			int c = i % m;
		
			//We are making calls to the very next box, hence No Row/Floor boundation
			//But implicitly Row/Floor boundation is there because of isSafeToPlaceQueen
			if (isSafeToPlaceQueen(board, r, c)) {
				board[r][c] = true;
				count += nqueen_01_combi(board, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
				board[r][c] = false;
			}
		}
		return count;
	}

	
	//Attacks, No Row/Floor boundation, 2D Grid (Subsequence)===================================================================
	public static int nqueen_01_combi_sub(boolean[][] board, int tnq, int idx, String ans) {
		int count = 0, n = board.length, m = board[0].length;
		if (tnq == 0 || idx == n * m) {
			if (tnq == 0) {
				System.out.println(ans);
			}
			return tnq == 0 ? 1 : 0;
		}

		int r = idx / m;
		int c = idx % m;
		
		//We are making calls to the very next box, hence No Row/Floor boundation
		//But implicitly Row/Floor boundation is there because of isSafeToPlaceQueen
		if (isSafeToPlaceQueen(board, r, c)) {
			board[r][c] = true;
			count += nqueen_01_combi_sub(board, tnq - 1, idx + 1, ans + "(" + r + "," + c + ") ");
			board[r][c] = false;
		}

		count += nqueen_01_combi_sub(board, tnq, idx + 1, ans);

		return count;
	}

	
	//Attacks, No Row/Floor boundation, 2D Grid (Permutation)===================================================================
	public static int nqueen_01_permu(boolean[][] board, int tnq, int idx, String ans) {
		if (tnq == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0, n = board.length, m = board[0].length;
		for (int i = idx; i < n * m; i++) {
			int r = i / m;
			int c = i % m;
			
			//We are making calls to the very next box, hence No Row/Floor boundation
			//But implicitly Row/Floor boundation is there because of isSafeToPlaceQueen
			
			//!board[r][c] will check 1+ queen should not sit in the same box
			//isSafeToPlaceQueen will check the attacks "around" the box,  
			if (!board[r][c] && isSafeToPlaceQueen(board, r, c)) {
				board[r][c] = true;
				count += nqueen_01_permu(board, tnq - 1, 0, ans + "(" + r + "," + c + ") ");
				board[r][c] = false;
			}
		}
		return count;
	}

	
	//============================================================================================================================
	
	//OPTIMIZING isSafeToPlaceQueen from O(n) to O(1)======================================================================
	
	public static boolean[] row;
	public static boolean[] col;
	public static boolean[] diag;
	public static boolean[] aDiag;

	
	//Attacks, No Row/Floor boundation, 2D Grid
	public static int nqueen_02_combi(int n, int m, int tnq, int idx, String ans) {
		if (tnq == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < n * m; i++) {
			int r = i / m;
			int c = i % m;
			
			//We are making calls to the very next box/room, hence No Row/Floor boundation
			//But implicitly Row/Floor boundation is there because of isSafeToPlaceQueen
			
			if (!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1]) {
				row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
				count += nqueen_02_combi(n, m, tnq - 1, i + 1, ans + "(" + r + ", " + c + ") ");
				row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
			}
		}
		return count;
	}

	
	//ATTACKS, ROW/FLOOR BOUNDATION=====================================================================================
	
	//Attacks, Row/Floor boundation, 2D Grid
	public static int nqueen_03_combi(int n, int m, int tnq, int floor, String ans) {
		if (tnq == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int room = 0; room < m; room++) {
			int r = floor, c = room;
			
			if (!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1]) {
				row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
				count += nqueen_03_combi(n, m, tnq - 1, floor + 1, ans + "(" + r + "," + c + ") ");
				row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
			}
		}
		return count;
	}

	//Attacks, Row/Floor boundation, 2D Grid
	public static int nqueen_03_permu(int n, int m, int tnq, int r, String ans) {
		if (tnq == 0 || r == n) {
			if (tnq == 0) {
				System.out.println(ans);
			}
			return tnq == 0 ? 1 : 0;
		}

		int count = 0;

		count += nqueen_03_permu(n, m, tnq, r + 1, ans);

		for (int c = 0; c < m; c++) {
			if (!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1]) {
				row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
				count += nqueen_03_permu(n, m, tnq - 1, 0, ans + "(" + r + ", " + c + ") ");
				row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
			}
		}
		return count;
	}

	
	//=========================================================================================================================
	
	public static int cols = 0;
	public static int diags = 0;
	public static int aDiags = 0;

	public static int nqueen_04_combi(int n, int floor) {
		if (floor == n) {
			return 1;
		}

		int count = 0, m = n;
		for (int room = 0; room < n; room++) {
			int r = floor, c = room;
			if ((cols & (1 << c)) == 0 && (diags & (1 << (r + c))) == 0 && (aDiags & (1 << (r - c + m - 1))) == 0) {
				cols ^= (1 << c);
				diags ^= (1 << (r + c));
				aDiags ^= (1 << (r - c + m - 1));

				count += nqueen_04_combi(n, floor + 1);

				cols ^= (1 << c);
				diags ^= (1 << (r + c));
				aDiags ^= (1 << (r - c + m - 1));
			}
		}
		return count;
	}

	//============================================================================================================================
	
	
	public static void nQueen() {
		int n = 4, m = 4;
		boolean[][] board = new boolean[4][4];
		int tnq = 4;

		System.out.println(nqueen_01_combi(board, tnq, 0, ""));
		System.out.println(nqueen_01_combi_sub(board, tnq, 0, ""));
		System.out.println(nqueen_01_permu(board, tnq, 0, ""));

		row = new boolean[n];
		col = new boolean[m];
		diag = new boolean[n + m - 1];
		aDiag = new boolean[n + m - 1];

		System.out.println(nqueen_03_combi(n, m, tnq, 0, ""));
	}


	public static void queenCombination() {
		int tnb = 4, tnq = 3;
		boolean[] boxes = new boolean[4];
		System.out.println(queenCombination1D(tnb, tnq, 0, 0, ""));
		System.out.println(queenPermutation1D(boxes, tnq, 0, 0, ""));

		boolean[][] board = new boolean[4][4];
		System.out.println(queenCombination2D(board, 4, 0, ""));
		System.out.println(queenPermutation2D(board, 4, 0, ""));
	}

	public static void main(String[] args) {
		// queenCombination();
		nQueen();
	}

}
