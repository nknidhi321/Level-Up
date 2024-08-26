// https://leetcode.com/problems/word-search-ii/
// This is a question of backtracking + trie
// But for you best sol is backtracking

// All solutions are same, ignore rest
// Mine, best, Sometimes(because of compiler) TLE but correct
```
class Solution {
    
    public static int n;
    public static int m;
    
    public List<String> findWords(char[][] board, String[] words) {    
        n = board.length;
        m = board[0].length;
        Set<String> set = new HashSet<>();
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(String word : words) {
                    if(board[i][j] == word.charAt(0)) {
                        if(dfs(i, j, 0, word, board, dir)) {
                            set.add(word);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
    
    public static boolean dfs(int row, int col, int idx, String word, char[][] board, int[][] dir) {
        if(board[row][col] != word.charAt(idx)) return false;
        if(idx + 1 == word.length()) return true;
        
        boolean found = false;
        char originalChar = board[row][col];
        board[row][col] = '$';
        for(int d = 0; d < 4; d++) {
            int x = row + dir[d][0];
            int y = col + dir[d][1];
            
            if(x >= 0 && x < n && y >= 0 && y < m && board[x][y] != '$') {
                if(dfs(x, y, idx + 1, word, board, dir)) {
                    found = true; //If you get the word you cannot return directly Why ?
                    break;
                }
            }
        }
        board[row][col] = originalChar; //Because you need to backtrack to generate "all" sol.
        return found;
    }
    
}
```
------------------------------------------------------------------------------------------------
//Kevin
```
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
       
        Set<String> set = new HashSet<>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                for(String str : words){
                    if(board[i][j] == str.charAt(0) && dfs(board, str, 0, i, j)) {
                        if(!set.contains(str)) {
                            set.add(str);
                       }
                    }
                }   
            }
        }
       return new ArrayList<>(set);
    }
    
    public boolean dfs(char[][] board, String word, int index, int i, int j) {
        
        if(index == word.length())
            return true;
        
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index))
            return false;
        
        char temp = board[i][j];
        board[i][j] = ' ';
        boolean result = dfs(board, word, index + 1, i - 1, j) || 
                         dfs(board, word, index + 1, i + 1, j) || 
                         dfs(board, word, index + 1, i, j - 1) || 
                         dfs(board, word, index + 1, i, j + 1);
        board[i][j] = temp;
        return result;
    }
}
```
---------------------------------------------------------------------------------------------------
//Rajneesh //Using dir matrix //Mine
```
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        HashSet<String> set = new HashSet<String>();
        for(String word : words){
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j] == word.charAt(0) && dfs(i, j, 0, word, dir, board) && !set.contains(word)) {
                        set.add(word);
                    }
                }
            }
        }
        return new ArrayList<String>(set);
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
------------------------------------------------------
