// https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1
// Left to right max finding
// Dp me kya store hoga ? Mere se aage tak ka maximum gold.

// Memoization

class Solution  {
    
    public static int maxGold(int n, int m, int M[][]) {
        
        Integer[][] dp = new Integer[n][m];
        
        int max = 0;
        for(int i = 0; i < M.length; i++) {
            max = Math.max(max, maxGold_Memo(i, 0, n, m, M, dp)); // Har ek row k starting se memo all lagao
        }
        return max;
    }
    
    public static int maxGold_Memo(int x, int y, int n, int m, int[][] M, Integer[][] dp) {
        if(y == m - 1) return dp[x][y] = M[x][y];
        
        if(dp[x][y] != null) return dp[x][y];
        
        int max1 = 0, max2 = 0, max3 = 0;
        
        if(x - 1 >= 0 && y + 1 < m) max1 = maxGold_Memo(x - 1, y + 1, n, m, M, dp);
        if(y + 1 < m) max2 = maxGold_Memo(x, y + 1, n, m, M, dp);
        if(x + 1 < n && y + 1 < m) max3 = maxGold_Memo(x + 1, y + 1, n, m, M, dp);
            
        return dp[x][y] = Math.max(max3, Math.max(max1, max2)) + M[x][y];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution  {
    
    public static int maxGold(int n, int m, int M[][]) {
        
        Integer[][] dp = new Integer[n][m];
        maxGold_Tab(n, m, M, dp);
        
        int max = 0;
        for(int i = 0; i < M.length; i++) {
            max = Math.max(max, dp[i][0]); // Finding max from the 1st column of dp
        }
        return max;
    }
    
    public static void maxGold_Tab(int n, int m, int[][] M, Integer[][] dp) {
		
		// Last col se solve hote hote, 1st column tak jaaega, since there's dependency on last column
        
        for(int y = m - 1; y >= 0; y--) { // Interchange row col, because dependency column pe hai
            for(int x = n - 1; x >= 0; x--) { // Interchange row col, So first column needs to be solved then row 
                if(y == m - 1) {
                    dp[x][y] = M[x][y];
                    continue;
                }
                
                int max1 = 0, max2 = 0, max3 = 0;
                
                if(x - 1 >= 0 && y + 1 < m) max1 = dp[x - 1][y + 1];   //maxGold_Memo(x - 1, y + 1, n, m, M, dp);
                if(y + 1 < m) max2 = dp[x][y + 1];  //maxGold_Memo(x, y + 1, n, m, M, dp);
                if(x + 1 < n && y + 1 < m) max3 = dp[x + 1][y + 1];    //maxGold_Memo(x + 1, y + 1, n, m, M, dp);
                    
                dp[x][y] = Math.max(max3, Math.max(max1, max2)) + M[x][y];
            }
        }
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// https://practice.geeksforgeeks.org/problems/path-in-matrix3805/1#
// Top to Bottom max finding
// Memoization

class Solution {
   
    public static int n, m;
    public static int[][] dir = {{1, 0}, {1, -1}, {1, 1}};
   
    public int maximumPath(int N, int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int max = 0;
        Integer[][] dp = new Integer[n][m];
        for(int j = 0; j < m; j++) {
            max = Math.max(max, minimumCostPath_Memo(0, j, grid, dp));
        }
        return max;
    }
   
    public static int minimumCostPath_Memo(int x, int y, int[][] grid, Integer[][] dp) {
        if(x == n - 1 && y == m - 1) return dp[x][y] = grid[x][y];
       
        if(dp[x][y] != null) return dp[x][y];
     
        int max = 0;
        for(int d = 0; d < dir.length; d++) {
            int r = x + dir[d][0];
            int c = y + dir[d][1];
           
            if(r >= 0 && c >= 0 && r < n && c < m) {
                max = Math.max(max, minimumCostPath_Memo(r, c, grid, dp));
            }
        }
        return dp[x][y] = max + grid[x][y];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
