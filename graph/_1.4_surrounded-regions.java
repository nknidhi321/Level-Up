//https://leetcode.com/problems/surrounded-regions/

/*
    Since you are expected to flip all the surrounded O's by X
    So, we can make a dfs call on all the 4 boundaries of the grid and mark the bounday O's with $ just  to distinguish between the boundary O's and other O's.

    Now, we wil simply iterate over the whole grid and do the following 2 steps:
    1) If we find O's make it as X (beacuse now whichever O's you will be finding in the entire grid that will be surrounded by X, since the boundary O's was converted into $)
    2) If we find $ make it as O

    Note: The order of these 2 steps is important, otherwise the end result will be all X's in the whole grid, which is not the desired output.
*/


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Using direction matrix:

class Solution {
    
    public void solve(char[][] board) {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        int row = board.length;
        int col = board[0].length;
        
        for(int i = 0 ; i < col; i++) {
            if(board[0][i] == 'O')
                dfs(board, 0, i, dir);
            if(board[row-1][i] == 'O')
                dfs(board, row-1, i, dir);
        }
        
        for(int i = 0 ; i < row; i++) {
            if(board[i][0] == 'O')
                dfs(board, i, 0, dir);
            if(board[i][col-1] == 'O')
                dfs(board, i, col-1, dir);
        }
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                if(board[i][j] == '$')
                    board[i][j] = 'O';
            }
        }
        return;
    }
    
    public static void dfs(char[][] board, int i, int j, int[][] dir) {
        
        board[i][j] = '$';
        
        for(int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            if(x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
                dfs(board, x, y, dir);
            }
        }
    }
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Without using direction matrix:

class Solution {
   
    public void solve(char[][] board) {
     
        int row = board.length;
        int col = board[0].length;
        
        for(int i = 0 ; i < col; i++){
            if(board[0][i] == 'O')
                dfs(board, 0, i);
            if(board[row-1][i] == 'O')
                dfs(board, row-1, i);
        }
        
        for(int i = 0 ; i < row; i++){
            if(board[i][0] == 'O')
                dfs(board, i, 0);
            if(board[i][col-1] == 'O')
                dfs(board, i, col-1);
        }
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                if(board[i][j] == '$')
                    board[i][j] = 'O';
            }
        }
        return;
    }
    
    public static void dfs(char[][] board, int i, int j){
        
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '$')
            return;
        
        board[i][j] = '$';
        dfs(board, i-1, j);
        dfs(board, i+1, j);
        dfs(board, i, j+1);
        dfs(board, i, j-1);
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
