// https://leetcode.com/problems/maximal-square/
// Some what similar to LIS

// Memoization
// Har cell pe stored hai, "mere pe khatam hone wala" max kitne length ka square bn paaega

```
class Solution {
    
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        Integer[][] dp = new Integer[n + 1][m + 1];
        
        // Either pass an ans array to keep track of overall max *
        int[] ans = new int[] {0};
        memo(n, m, matrix, dp, ans);
        return ans[0] * ans[0];

        // * Or iterate over the dp array to find the max length of square 
        
        // int max = 0;
        // for(int i = 0; i < dp.length; i++) {
        //     for(int j = 0; j < dp[0].length; j++) {
        //         max = Math.max(max, dp[i][j]);
        //     }
        // }
        // return max * max;
    }
    
    public int memo(int n, int m, char[][] matrix, Integer[][] dp, int[] ans) {
        if(n == 0 || m == 0) return dp[n][m] = 0;
        
        if(dp[n][m] != null) return dp[n][m];

        // 3ino me se koi call nahi lgi aisa kvi nai hoga
        // So, 0 toh hamesha jitega he Integer.MAX_VALUE se, so min + 1 me overflow ki dikkat nai aaegi
        int min = Integer.MAX_VALUE; 
        if(n - 1 >= 0) min = Math.min(min, memo(n - 1, m, matrix, dp, ans));
        if(m - 1 >= 0) min = Math.min(min, memo(n, m - 1, matrix, dp, ans));
        if(n - 1 >= 0 && m - 1 >= 0) min = Math.min(min, memo(n - 1, m - 1, matrix, dp, ans));

        // Agar mai '1' hu, toh apne top, diagonal top left, and apne left ka min dekhungi
        // Unpe khatam hone wala min value he mere square ka length set karega
        if(matrix[n - 1][m - 1] == '1') { 
            ans[0] = Math.max(ans[0], min + 1); // Mere naam ka +1 karungi
            return dp[n][m] = min + 1; // Mere naam ka +1 karungi
        }
        else return dp[n][m] = 0; // Agar mai '0' hu toh mai toh sq. bna he nai sakti
    }
    
}
```

-----------------------------------------------------------------------------------------------------------------------

// Tabulation
// Har cell pe stored hai, mere pe khatam hone wala max kitne length ka square bn paaega

```
class Solution {
    
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        Integer[][] dp = new Integer[n + 1][m + 1];
        
        // Either pass an ans array to keep track of overall max *
        int[] ans = new int[] {0};
        tab(n, m, matrix, dp, ans);
        return ans[0] * ans[0];

        // * Or iterate over the dp array to find the max length of square 
        
        // int max = 0;
        // for(int i = 0; i < dp.length; i++) {
        //     for(int j = 0; j < dp[0].length; j++) {
        //         max = Math.max(max, dp[i][j]);
        //     }
        // }
        // return max * max;
    }
    
    public void tab(int N, int M, char[][] matrix, Integer[][] dp, int[] ans) {
        for(int n = 0; n <= N; n++) {
            for(int m = 0; m <= M; m++) {
                if(n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                // 3ino me se koi call nahi lgi aisa kvi nai hoga
                // So, 0 toh hamesha jitega he Integer.MAX_VALUE se, so min + 1 me overflow ki dikkat nai aaegi
                int min = Integer.MAX_VALUE; 
                if(n - 1 >= 0) min = Math.min(min, dp[n - 1][m]); // memo(n - 1, m, matrix, dp, ans);
                if(m - 1 >= 0) min = Math.min(min, dp[n][m - 1]); // memo(n, m - 1, matrix, dp, ans));
                if(n - 1 >= 0 && m - 1 >= 0) min = Math.min(min, dp[n - 1][m - 1]); //memo(n - 1, m - 1, matrix, dp, ans));

                // Agar mai '1' hu, toh apne top, diagonal top left, and apne left ka min dekhungi
                // Unpe khatam hone wala min value he mere square ka length set karega
                if(matrix[n - 1][m - 1] == '1') { 
                    ans[0] = Math.max(ans[0], min + 1); // Mere naam ka +1 karungi
                    dp[n][m] = min + 1; // Mere naam ka +1 karungi
                }
                else dp[n][m] = 0; // Agar mai '0' hu toh mai toh sq. bna he nai sakti
            }
        }
    }
    
}
```

--------------------------------------------------------------------------------------------------------------------------------
