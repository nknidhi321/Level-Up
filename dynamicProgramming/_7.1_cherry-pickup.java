// https://leetcode.com/problems/cherry-pickup/

class Solution {
    
    public int n;
    
    public int cherryPickup(int[][] grid) {
        n = grid.length;
        Integer[][][][] dp = new Integer[n][n][n][n];
        
        // NOTE : Wrong approach :
        // 1 bnda (0,0) se chala le with "max" cost path(greedily)
        // And (n-1, m-1) se wapas aaye with "max" cost path(greedily) 
        // Will not give you correct answer. Refer pep YT for TC
        
        // Correct approach :
        // Jaate waqt greedily na jaaye, and aate waqt greedily chale jaaye tab baat bn jaaegi
        // A->B jaane me jitna cost lgega, utna he B->A jaane me lgega, so q na 2 bnde chala de (0,0) se he.
        // So, 2 bnde chala lo (0,0) note ab greedily he jaane hai => "max cost path", kuki max cost path tvi milega    
        // Par yahi agar individually kar rahe hote Jaane ka "max cost path" + aane ka "max cost path" => Galat ans
        
        // Conclusion : Ek he destinaton hai tab "max cost path" => Greedily
        // Alag alag destination hai "Greedily sirf wapas aate waqt lagao", jaate waqt greedily kaam nahi kar saktey
        
        int ans = cp(0, 0, 0, 0, grid, dp); // Implementing 2 bnde from (0, 0)
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }
    
    public int cp(int r1, int c1, int r2, int c2, int[][] grid, Integer[][][][] dp) {
        // out of bounds
        if(r1 >= n || r2 >= n || c1 >= n || c2 >= n || grid[r1][c1] == - 1 || grid[r2][c2] == -1) return Integer.MIN_VALUE;
        
        // destination
        if(r1 == n - 1 && c1 == n - 1 && r2 == n - 1 && c2 == n - 1) return dp[r1][c1][r2][c2] = grid[r1][c1];
        
        // repeating tree stored dp result
        if(dp[r1][c1][r2][c2] != null) return dp[r1][c1][r2][c2];
        
        // 2 * 2 ways for 2 people
        int max = Integer.MIN_VALUE;
        max = Math.max(max, cp(r1 + 1, c1, r2 + 1, c2, grid, dp)); // h, h
        max = Math.max(max, cp(r1, c1 + 1, r2, c2 + 1, grid, dp)); // v, v
        max = Math.max(max, cp(r1 + 1, c1, r2, c2 + 1, grid, dp)); // h, v
        max = Math.max(max, cp(r1, c1 + 1, r2 + 1, c2, grid, dp)); // v, h
        
        // destination pe kvi jaa he nai paaye, (n-1, m-1) cell blocked hai
        if(max == Integer.MIN_VALUE) return dp[r1][c1][r2][c2] = Integer.MIN_VALUE;
        else { // destination se waapas aa kar yaha gire hai
            if(r1 == r2 && c1 == c2) return dp[r1][c1][r2][c2] = max + grid[r1][c1]; // same cell pe hai dono  
            else return dp[r1][c1][r2][c2] = max + grid[r1][c1] + grid[r2][c2]; // diff cell pe hai dono
        }
    }
    
}
