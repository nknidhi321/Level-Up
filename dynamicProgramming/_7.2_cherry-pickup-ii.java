// https://leetcode.com/problems/cherry-pickup-ii/

class Solution {
    
    public int n, m;
    
    public int cherryPickup(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int max = Math.max(n, m);
        Integer[][][] dp = new Integer[max][max][max];
        
        // Optimal or max path ?
        return cp(0, 0, m - 1, grid, dp); // Implementing 2 bnde from (top left, top right)
    }
    
    public int cp(int r, int c1, int c2, int[][] grid, Integer[][][] dp) {
        // out of bounds
        if(r >= n || c1 < 0 || c1 >= m || c2 < 0 || c2 >= m) return Integer.MIN_VALUE;
        
        // destination
        if(r == n - 1) {
            if(c1 == c2) return dp[r][c1][c2] = grid[r][c1]; // same cell
            else return dp[r][c1][c2] = grid[r][c1] + grid[r][c2]; // diff cell
        }
        
        // repeating tree stored dp result
        if(dp[r][c1][c2] != null) return dp[r][c1][c2];
        
        // 3 * 3 ways for 2 people, since 3 directions
        int max = Integer.MIN_VALUE;
        for(int j1 = -1; j1 <= 1; j1++) {
            for(int j2 = -1; j2 <= 1; j2++) {
                max = Math.max(max, cp(r + 1, c1 + j1, c2 + j2, grid, dp));
            }
        }
        
        if(c1 == c2) return dp[r][c1][c2] = max + grid[r][c1]; // same cell pe hai dono  
        else return dp[r][c1][c2] = max + grid[r][c1] + grid[r][c2]; // diff cell pe hai dono
    }

}
