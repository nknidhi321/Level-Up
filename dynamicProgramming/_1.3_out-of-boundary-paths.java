// https://leetcode.com/problems/out-of-boundary-paths/
// Visualize in terms of states, array me 3D DP bna k smjhne ki kosish mat karna
// NOTE : OutOfBoundary jaane k liye v ek move chahiye

```
class Solution {
    
    public int mod = (int)1e9 + 7;
    public int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public int findPaths(int n, int m, int maxMove, int startRow, int startColumn) {
        
        // Why maxMove + 1 ?? Because maxMove == 0 pe v store krna hai
        Integer[][][] dp = new Integer[n][m][maxMove + 1]; 
        return findPaths_Memo(startRow, startColumn, n, m, maxMove, dp);
    }
    
    public int findPaths_Memo(int r, int c, int n, int m, int maxMove, Integer[][][] dp) {
        
        // So, OutOfBoundary jaane k "baad" v agar maxMove 0 ya us se zyada hai then you got a path
        if(r < 0 || r == n || c < 0 || c == m) return 1;  
        
        // So, OutOfBoundary jaane se pehle he agar maxMove 0 ho gayi toh ab kuch nai ho sakta
        if(maxMove == 0) return 0;
        
        // Yaha visited rakhne ki zaroorat q nahi hai, inspite of having 4 dir calls ??
        // Kuki yaha agar hum already solved state pe chale v gaye then waha se sidha dp se answer return krwa lenge
        // As in, waha koi tree nai bn ne wala, so no need to keep visited matrix
        
        if(dp[r][c][maxMove] != null) return dp[r][c][maxMove];        
        
        int count = 0;
        for(int d = 0; d < dir.length; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];
            count = (count + findPaths_Memo(x, y, n, m, maxMove - 1, dp)) % mod;
        }
        return dp[r][c][maxMove] = count;
    }
    
}
```
-------------------------------------------------------------------------------------------------------------------------------
