// https://leetcode.com/problems/maximal-square/
// Some what similar to LIS


// In memo
// Ek extra row and col leke chalna he parega, ye dono TC dekh lo 
// [["1","0"]] isme 0 se he return ho jaaoge 1 pe kvi gya he nai

// [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
// Iski dp print krwa k dekh lo
// 1st col me 1234 bhr gaya 

// In tab
// No need to take extra row col, kuki tum traval he (0, 0) se karoge

// Wrong for memo, works in tab if you do not take extra row && col
// Without Using extra row col
class Solution {
    
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] dp = new int[n][m];
        for(int[] d : dp) Arrays.fill(d, -1);
        
        // Either pass an ans array to keep track of overall max *
        int[] ans = new int[] {0};
        tab(n - 1, m - 1, matrix, dp, ans);
        return ans[0] * ans[0];

        // // *Or iterate over the dp array to find the max length of square       
        // int max = 0;
        // for(int i = 0; i < dp.length; i++) {
        //     for(int j = 0; j < dp[0].length; j++) {
        //         System.out.print(dp[i][j] + "\t");
        //         max = Math.max(max, dp[i][j]);
        //     }
        //     System.out.println();
        // }
        // return max * max;
    }
    
    public void tab(int I, int J, char[][] matrix, int[][] dp, int[] ans) {
        
        for(int i = 0; i <= I; i++) {
            for(int j = 0; j <= J; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                    ans[0] = Math.max(ans[0], dp[i][j]);
                    continue;
                }

                // NOTE : Agar mai '0' hu that does mean we should return, aage ki calls v lgegi

                // 3ino me se koi call nahi lgi aisa kvi nai hoga
                // So, 0 toh hamesha jitega he Integer.MAX_VALUE se, so min + 1 me overflow ki dikkat nai aaegi
                int top = Integer.MAX_VALUE, left = Integer.MAX_VALUE, dia = Integer.MAX_VALUE; 
                if(i - 1 >= 0) top = dp[i - 1][j]; // memo(i - 1, j, matrix, dp, ans);
                if(j - 1 >= 0) left = dp[i][j - 1]; // memo(i, j - 1, matrix, dp, ans);
                if(i - 1 >= 0 && j - 1 >= 0) dia = dp[i - 1][j - 1]; // memo(i - 1, j - 1, matrix, dp, ans);

                if(matrix[i][j] == '0') dp[i][j] = 0; // Agar mai '0' hu toh mai toh sq. bna he nai sakti
                else { // Agar mai '1' hu, toh apne top, diagonal top left, and apne left ka min dekhungi
                    int min = Math.min(dia, Math.min(top, left));// Unpe ktm hone wala min value he mere sq. ka len set karega
                    dp[i][j] = min + 1; // Mere naam ka +1 karungi
                }
                
                ans[0] = Math.max(ans[0], dp[i][j]);
            }
        }
    }
    
}

----------------------------------------------------------------------------------------------------------------------------------------------
// Using extra row col
// Memoization
// Har cell pe stored hai, "mai ending cell hu and mere pe khatam hone wala" max kitne length ka square bn paaega

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
// Using extra row col
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
