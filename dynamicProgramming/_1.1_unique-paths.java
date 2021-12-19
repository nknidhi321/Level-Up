// https://leetcode.com/problems/unique-paths/

// Memoization

class Solution {
    
    public int uniquePaths(int n, int m) {
        Integer[][] dp = new Integer[n][m];
        return uniquePaths_memo(0, 0, n - 1, m - 1, dp);
    }
    
    public static int uniquePaths_memo(int sr, int sc, int er, int ec, Integer[][] dp){
        if(sr == er && sc == ec) return dp[sr][sc] = 1;
        
        if(dp[sr][sc] != null) return dp[sr][sc];
        
        int count = 0;
        if(sr + 1 <= er) count += uniquePaths_memo(sr + 1, sc, er, ec, dp);
        if(sc + 1 <= ec) count += uniquePaths_memo(sr, sc + 1, er, ec, dp);
        return dp[sr][sc] = count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution {
    
    public int uniquePaths(int n, int m) {
        Integer[][] dp = new Integer[n][m];
        return uniquePaths_tab(0, 0, n - 1, m - 1, dp);
    }
    
    public static int uniquePaths_tab(int SR, int SC, int er, int ec, Integer[][] dp){
        for(int sr = er; sr >= 0; sr--) {
            for(int sc = ec; sc >= 0; sc--) {
                if(sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                if(sr + 1 <= er) count += dp[sr + 1][sc];   // uniquePaths_memo(sr + 1, sc, er, ec, dp);
                if(sc + 1 <= ec) count += dp[sr][sc + 1];   // uniquePaths_memo(sr, sc + 1, er, ec, dp);
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


[indent]
mode = "tabs"		# It's either "spaces" or "tabs".
size = 4
