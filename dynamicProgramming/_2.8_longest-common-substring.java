// https://practice.geeksforgeeks.org/problems/longest-common-substring1452/1#
// Substring question directly go for tabulation

// Tabulation

class Solution{
    
    int longestCommonSubstr(String s1, String s2, int n, int m) {
        Integer[][] dp = new Integer[n + 1][m + 1];
        return longestCommonSubstr_Tab(n, m, s1, s2, dp);
    }
    
    public static int longestCommonSubstr_Tab(int N, int M, String s1, String s2, Integer[][] dp) {
        int maxLen = 0, ei = 0;
        
        for(int n = 0; n <= N; n++) {
            for(int m = 0; m <= M; m++) {
                if(n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if(s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                }
                else {
                    dp[n][m] = 0;
                }
                
                if(dp[n][m] > maxLen) {
                    maxLen = dp[n][m];
                    ei = n - 1;
                }
            }
        }
        return maxLen;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
