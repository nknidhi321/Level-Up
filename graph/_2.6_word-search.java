// https://leetcode.com/problems/word-search/

// Mine, best
// Ignore rest, all same
```
class Solution {
    
    public static int n;
    public static int m;
    
    public boolean exist(char[][] board, String word) {    
        n = board.length;
        m = board[0].length;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(i, j, 0, word, board, dir)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean dfs(int row, int col, int idx, String word, char[][] board, int[][] dir) {
        if(board[row][col] != word.charAt(idx)) return false;
        if(idx + 1 == word.length()) return true;
        
        char originalChar = board[row][col];
        board[row][col] = '$';
        for(int d = 0; d < 4; d++) {
            int x = row + dir[d][0];
            int y = col + dir[d][1];
            
            if(x >= 0 && x < n && y >= 0 && y < m && board[x][y] != '$') {
                if(dfs(x, y, idx + 1, word, board, dir)) {
                    return true;
                }
            }
        }
        board[row][col] = originalChar;
        return false;
    }
}
```
-------------------------------------------------------------
//Kevin
```
class Solution {
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0) && dfs(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }
    
    public static boolean dfs(char[][] board, String word, int index,  int i, int j){
        if(index == word.length())
            return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index))
            return false;
       
        char temp = board[i][j];
        board[i][j] = ' ';
        
        boolean result = dfs(board, word, index + 1, i-1, j) || 
                         dfs(board, word, index + 1, i+1, j) || 
                         dfs(board, word, index + 1, i, j-1) || 
                         dfs(board, word, index + 1, i, j+1);
        
        board[i][j] = temp;
        return result;
    }
}
```
-----------------------------------------------------------------------------------------------
//Rajneesh //Using dir matrix
```
class Solution {
    public boolean exist(char[][] board, String word) {
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0) && dfs(i, j, 0, word, dir, board)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean dfs(int i, int j, int idx, String word, int[][] dir, char[][] board) {
       
        if(idx == word.length())
            return true;
        
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(idx)) {
            return false;
        }
        
        char temp = board[i][j];
        board[i][j] = '$';
        
        boolean result = false;
        
        for(int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            if(dfs(x, y, idx + 1, word, dir, board)) {
                result = true;
                break;
            }
        }
        
        board[i][j] = temp;
        return result;
    }
}
```
---------------------------------------------------------
