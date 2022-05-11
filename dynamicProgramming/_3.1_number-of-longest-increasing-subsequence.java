// https://leetcode.com/problems/number-of-longest-increasing-subsequence/

class Solution {
    
    public int findNumberOfLIS(int[] arr) {
        int n = arr.length;
        
        int[] dp = new int[n];
        int maxLIS_len = 0;
        
        int[] count = new int[n];
        int maxLIS_count = 0;
        
        for(int i = 0; i < n; i++) {
            dp[i] = count[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] < arr[i]) {
                    if(dp[j] + 1 > dp[i]) { // Mere se chote len ka
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; 
                        // Jo mere se chote honge unke piche chipak k mai utna he LIS count bna paaungi
                    }
                    else if(dp[j] + 1 == dp[i]) { // Mere len ka 
                        count[i] += count[j];
                    }
                }
            }
            
            // Cal. overall maxLIS_len and maxLIS_count of that length
            if(dp[i] > maxLIS_len) {
                maxLIS_len = dp[i];
                maxLIS_count = count[i];
            }
            else if(dp[i] == maxLIS_len) {
                maxLIS_count += count[i];
            }
            
        }
        return maxLIS_count;        
    }
    
}
