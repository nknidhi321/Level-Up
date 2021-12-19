// https://leetcode.com/problems/unique-paths-ii/

// Memoization

class Solution {
    
    public static int n, m;
    public static int[][] dir = {{0, 1}, {1, 0}};
  
    public int uniquePathsWithObstacles(int[][] arr) {
        n = arr.length;
        m = arr[0].length;
        
        if(arr[0][0] == 1 || arr[n - 1][m - 1] == 1) return 0;
        
        Integer[][] dp = new Integer[n][m];
        return uniquePathsWithObstacles_Memo(0, 0, arr, dp);    
    }
    
    public static int uniquePathsWithObstacles_Memo(int sr, int sc, int[][] arr, Integer[][] dp){
        if(sr == n - 1 && sc == m - 1) return dp[sr][sc] = 1;
        
        if(dp[sr][sc] != null) return dp[sr][sc];
        
        int count = 0;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            
            if(r <= arr.length - 1 && c <= arr[0].length - 1 && arr[r][c] == 0){
                 count += uniquePathsWithObstacles_Memo(r, c, arr, dp);
            }
        }
        return dp[sr][sc] = count;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution {
    
    public static int n, m;
    public static int[][] dir = {{0, 1}, {1, 0}};
  
    public int uniquePathsWithObstacles(int[][] arr) {
        n = arr.length;
        m = arr[0].length;
        
        if(arr[0][0] == 1 || arr[n - 1][m - 1] == 1) return 0;
        
        Integer[][] dp = new Integer[n][m];
        return uniquePathsWithObstacles_Tab(0, 0, arr, dp);
    }
    
    public static int uniquePathsWithObstacles_Tab(int SR, int SC, int[][] arr, Integer[][] dp){
        
        for(int sr = n - 1; sr >= 0; sr--) {
            for(int sc = m - 1; sc >= 0; sc--) {
                if(sr == n - 1 && sc == m - 1) {
                    dp[sr][sc] = 1;
                    continue;
                }
                
                int count = 0;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    
                    if(r < n && c < m && arr[r][c] == 0) {
                         count += dp[r][c];
                    }
                }
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
