// https://leetcode.com/problems/uncrossed-lines/

// Ditto same as LCS
// Uncrossed lines will be made sure if we take subsequence (Order will be preserved)

// Memoization

class Solution {
    
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        Integer[][] dp = new Integer[n + 1][m + 1];
        return maxUncrossedLines_Memo(n, m, nums1, nums2, dp);
    }
    
    public static int maxUncrossedLines_Memo(int n, int m, int[] nums1, int[] nums2, Integer[][] dp) {
        if(n == 0 || m == 0) return dp[n][m] = 0;
        
        if(dp[n][m] != null) return dp[n][m];
        
        if(nums1[n - 1] == nums2[m - 1]) {
            return dp[n][m] = maxUncrossedLines_Memo(n - 1, m - 1, nums1, nums2, dp) + 1;
        }
        else {
            int max1 = 0, max2 = 0;
            max1 = maxUncrossedLines_Memo(n - 1, m, nums1, nums2, dp);
            max2 = maxUncrossedLines_Memo(n, m - 1, nums1, nums2, dp);
            return dp[n][m] = Math.max(max1, max2);
        }
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution {
    
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        Integer[][] dp = new Integer[n + 1][m + 1];
        return maxUncrossedLines_Tab(n, m, nums1, nums2, dp);
    }
    
    public static int maxUncrossedLines_Tab(int N, int M, int[] nums1, int[] nums2, Integer[][] dp) {
        for(int n = 0; n <= N; n++) {
            for(int m = 0; m <= M; m++) {
                if(n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if(nums1[n - 1] == nums2[m - 1]) {
                    dp[n][m] = dp[n - 1][m - 1] + 1;    // maxUncrossedLines_Tab(n - 1, m - 1, nums1, nums2, dp);
                }
                else {
                    int max1 = 0, max2 = 0;
                    max1 = dp[n - 1][m];    // maxUncrossedLines_Tab(n - 1, m, nums1, nums2, dp);
                    max2 = dp[n][m - 1];    // maxUncrossedLines_Tab(n, m - 1, nums1, nums2, dp);
                    dp[n][m] = Math.max(max1, max2);
                }
            }
        }
        return dp[N][M];
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
