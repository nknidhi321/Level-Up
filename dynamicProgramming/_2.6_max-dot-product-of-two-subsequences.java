// https://leetcode.com/problems/max-dot-product-of-two-subsequences/

// Memoization

class Solution {
    
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        Integer[][] dp = new Integer[n + 1][m + 1];
        return maxDotProduct_Memo(n, m, nums1, nums2, dp);
    }
    
    public static int maxDotProduct_Memo(int n, int m, int[] nums1, int[] nums2, Integer[][] dp) {
        if(n == 0 || m == 0) return dp[n][m] = -(int)1e9;
        
        if(dp[n][m] != null) return dp[n][m];
           
        // When rest all gives smaller res except NM
        int only_NM = nums1[n - 1] * nums2[m - 1];
        
        int prevMaxDotProduct = maxDotProduct_Memo(n - 1, m - 1, nums1, nums2, dp);
        int prevMaxDotProduct_plus_only_NM = prevMaxDotProduct + only_NM;
        
        // exclude_NM(prevMaxDotProduct) will be covered here, at some point in tree Diagram
        int exclude_N = maxDotProduct_Memo(n - 1, m, nums1, nums2, dp);
        int exclude_M = maxDotProduct_Memo(n, m - 1, nums1, nums2, dp);
        
        return dp[n][m] = max(only_NM, prevMaxDotProduct_plus_only_NM, exclude_N, exclude_M);
    }
    
    public static int max(int... arr) { // Can take any number of args and form into an array
        int max = arr[0];
        for(int val : arr) {
            max = Math.max(val, max);
        }
        return max;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution {
    
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        Integer[][] dp = new Integer[n + 1][m + 1];
        return maxDotProduct_Tab(n, m, nums1, nums2, dp);
    }
    
    
    public static int maxDotProduct_Tab(int N, int M, int[] nums1, int[] nums2, Integer[][] dp) {
        
        for(int n = 0; n <= N; n++) {
            for(int m = 0; m <= M; m++) {
                if(n == 0 || m == 0) {
                    dp[n][m] = -(int)1e9;
                    continue;
                }

                // When rest all gives smaller res except NM
                int only_NM = nums1[n - 1] * nums2[m - 1];

                int prevMaxDotProduct = dp[n - 1][m - 1];   // maxDotProduct_Memo(n - 1, m - 1, nums1, nums2, dp);
                int prevMaxDotProduct_plus_only_NM = prevMaxDotProduct + only_NM;

                // exclude_NM(prevMaxDotProduct) will be covered here, at some point in tree Diagram
                int exclude_N = dp[n - 1][m];   // maxDotProduct_Memo(n - 1, m, nums1, nums2, dp);
                int exclude_M = dp[n][m - 1];   // maxDotProduct_Memo(n, m - 1, nums1, nums2, dp);

                dp[n][m] = max(only_NM, prevMaxDotProduct_plus_only_NM, exclude_N, exclude_M);
            }
        }
        return dp[N][M];
    }

    
    public static int max(int... arr) { // Can take any number of args and form into an array
        int max = arr[0];
        for(int val : arr) {
            max = Math.max(val, max);
        }
        return max;
    }
    
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
