// https://leetcode.com/problems/n-queens-ii/

/*
	  Row on level
	  Shadow Technique | Branch & Bound
	  
	  Rajneesh  
	  Efficient isSafe() by creating col, dia and antiDia
	  
	  NOTE : If queen is placed in a row, we move to the next row => Only one queen will be placed in a row
	  So, no need of keeping row[] array, because it is queen combination 
*/

NOTE :	[ If "queen permutation" was the question then you also require row check and that very box check where you want to place the queen ]


class Solution {
        
    public int totalNQueens(int n) {
        
        boolean[] col = new boolean[n];
        boolean[] dia = new boolean[2 * n - 1]; // n + n - 1
        boolean[] antiDia = new boolean[2 * n - 1]; // n + n - 1
        
        return totalNQueens_Combi(n, 0, col, dia, antiDia);
    }
    
    public static int totalNQueens_Combi(int n, int r, boolean[] col, boolean[] dia, boolean[] antiDia) {
        if(r == n) return 1;
        
        int count = 0;
        for(int c = 0; c < n; c++) {
            if(!col[c] && !dia[r + c] && !antiDia[r - c + n - 1]) {
                col[c] = dia[r + c] = antiDia[r - c + n - 1] = true;
                count += totalNQueens_Combi(n, r + 1, col, dia, antiDia);
                col[c] = dia[r + c] = antiDia[r - c + n - 1] = false;
            }
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
	Column on level
	
	Mine Old code
	Not efficient isSafe() 
	Note : Only 3 direction is enough to check for attack
	[No need to check for column because you move to the very next column once a queen is placed]
*/

class Solution {
    
    public int totalNQueens(int n) {
        
        char[][] board = new char[n][n];
        for(int i = 0; i < n ; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        return totalNQueens_Combi(0, board);
    }
    
    public static boolean isSafe(char[][] board, int row, int col) {
        
        // Left Horizontal
        for(int i = col - 1; i >= 0; i--) {
            if(board[row][i] == 'Q')
                return false;
        }
        
        // Top antiDiagonal
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 'Q')
                return false;
        }
        
        // Down diagonal
        for(int i = row + 1, j = col - 1; i < board.length && j >= 0; i++, j--) {
            if(board[i][j] == 'Q')
                return false;
        }
        
        return true;
    }

    
    public static int totalNQueens_Combi(int col, char[][] board) {
        if(col == board.length) return 1;
        
        int count = 0;
        for(int row = 0; row < board.length; row++) {
            if(isSafe(board, row, col)){
                board[row][col] = 'Q';
                count += totalNQueens_Combi(col + 1, board);
                board[row][col] = '.';
            }
        }
        return count;        
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
