//https://leetcode.com/problems/n-queens/

class Solution {
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        
        char[][] board = new char[n][n];
        for(int i = 0; i < n ; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        NQueens_Combi(0, board, res);
        return res;
    }
    
    public static void NQueens_Combi(int col, char[][] board, List<List<String>> res){
        if(col == board.length) {
            List<String> list = new ArrayList<>();
            for(int i = 0; i < board.length; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < board.length; j++) {
                    sb.append(board[i][j]);
                }
                list.add(sb.toString());
            }
            res.add(list);
            return;
        }
        
        for(int row = 0; row < board.length; row++) {
            if(isSafe(board, row, col)){
                board[row][col] = 'Q';
                NQueens_Combi(col + 1, board, res);
                board[row][col] = '.';
            }
        }
        
    }
    
    public static boolean isSafe(char[][] board, int row, int col) {
        for(int i = col - 1; i >= 0; i--){
            if(board[row][i] == 'Q')
                return false;
        }
        
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 'Q')
                return false;
        }
        
        for(int i = row + 1, j = col - 1; i < board.length && j >= 0; i++, j--) {
            if(board[i][j] == 'Q')
                return false;
        }
        return true;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
