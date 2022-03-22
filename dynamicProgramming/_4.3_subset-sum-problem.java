// https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/ 
// If infinite supply of coins is not mentioned then, consider it as, coins can be used only once

// Memoization

class Solution {

    public static Boolean isSubsetSum(int n, int arr[], int tar) {
        
        Integer[][] dp = new Integer[n + 1][tar + 1];
        return isSubsetSum_Memo(n, arr, tar, dp) == 1 ? true : false;
    }
    
    public static int isSubsetSum_Memo(int n, int[] arr, int tar, Integer[][] dp) {
        if(n == 0 || tar == 0) return dp[n][tar] = tar == 0 ? 1 : 0; 
        
        if(dp[n][tar] != null) return dp[n][tar];
        
        boolean res = false;
        res = res || isSubsetSum_Memo(n - 1, arr, tar, dp) == 1; // Exclude
        
        if(tar - arr[n - 1] >= 0) {
            res = res || isSubsetSum_Memo(n - 1, arr, tar - arr[n - 1], dp) == 1; // Include
        }
        return dp[n][tar] = res ? 1 : 0;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution {

    public static Boolean isSubsetSum(int n, int arr[], int tar) {
        
        Integer[][] dp = new Integer[n + 1][tar + 1];
        return isSubsetSum_Memo(n, arr, tar, dp) == 1 ? true : false;
    }
    
    public static int isSubsetSum_Memo(int n, int[] arr, int tar, Integer[][] dp) {
        if(n == 0 || tar == 0) return dp[n][tar] = tar == 0 ? 1 : 0; 
        
        if(dp[n][tar] != null) return dp[n][tar];
        
        boolean res = false;
        if(tar - arr[n - 1] >= 0) {
            res = res || isSubsetSum_Memo(n - 1, arr, tar - arr[n - 1], dp) == 1; // Include
        }
        res = res || isSubsetSum_Memo(n - 1, arr, tar, dp) == 1; // Exclude
        return dp[n][tar] = res ? 1 : 0;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
