//https://leetcode.com/problems/number-of-enclaves/
Bich wale kitne 1's hai find karo

/*
  Surrounding region similar question
  
  Iterate the whole matrix and make a dfs call from all boundaries having 1, and mark it as 0.
  Now, again iterate the whole matrix and count all 1's in the grid and return

*/

class Solution {
    
    public int numEnclaves(int[][] grid) {
    
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j++) {
                if(i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    if(grid[i][j] == 1) {
                        dfs(i, j, dir, grid);  
                    }
                }
            }
        }
        
        int count = 0;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    count++;  
                }
            }
        }
        return count;
    }
    
     public static void dfs(int i, int j , int[][] dir, int[][] grid) {
        grid[i][j] = 0;
        
        for(int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                dfs(x, y, dir, grid);
            }
        }
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
