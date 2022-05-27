// https://leetcode.com/problems/minimum-path-sum/ 

class Solution {
    
    int n, m;
    int[][] dir = {{0, 1}, {1, 0}};
    
    public int minPathSum(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        Integer[][] dp = new Integer[n][m]; 
        return minPathSum_Memo(0, 0, dp, grid);
    }
    
    public int minPathSum_Memo(int r, int c, Integer[][] dp, int[][] grid) {
        if(dp[r][c] != null) return dp[r][c];
        
        int min = Integer.MAX_VALUE;
        for(int d = 0; d < dir.length; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];
            
            if(x < n && y < m) {
                min = Math.min(min, minPathSum_Memo(x, y, dp, grid));
            }
        }
        if(min == Integer.MAX_VALUE) min = 0;
        return dp[r][c] = min + grid[r][c];
    }
    
}
