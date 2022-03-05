//https://leetcode.com/problems/number-of-enclaves/
Bich wale kitne 1s hai find karo

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
        
        // Making dfs call on all 1's boundary, and marking it as -1
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j++) {
                if(i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    if(grid[i][j] == 1) {
                        dfs(i, j, dir, grid);  
                    }
                }
            }
        }
        
        
        // Now, Count all 1's (which is bich wale 1's)
        // Also mark the -1 cells as 1, just for not modifing the original grid
        int count = 0;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) count++;  // Counting 1's
                else if(grid[i][j] == -1) grid[i][j] = 1; // Make the grid as how it was originally
            }
        }
        return count;
    }
    
     
     public static void dfs(int i, int j , int[][] dir, int[][] grid) {
        grid[i][j] = -1;
        
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
