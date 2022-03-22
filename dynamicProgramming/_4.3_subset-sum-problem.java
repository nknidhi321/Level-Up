// https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/ 
// If infinite supply of coins is not mentioned then, consider it as, coins can be used only once
// Not using boolean DP because false is also ananswer, Boolean DP could have been used.

// Subsequence 
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

//----------------------------------------------------------------------------------------------

// Tabulation


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// P&C method, for loop
// Memoization

class Solution {

    static Boolean isSubsetSum(int N, int[] arr, int tar) {
        Integer[][] dp = new Integer[N + 1][tar + 1];
        return isSubsetSum_Memo(0, arr, tar, dp) == 1 ? true : false;
    }
    
    public static int isSubsetSum_Memo(int idx, int[] arr, int tar, Integer[][] dp) {
        if(tar == 0 || idx == arr.length) return dp[idx][tar] = (tar == 0) ? 1 : 0;
        
        if(dp[idx][tar] != null) return dp[idx][tar];
        
        for(int i = idx; i < arr.length; i++) {
            if(tar - arr[i] >= 0) {
                if(isSubsetSum_Memo(i + 1, arr, tar - arr[i], dp) == 1) {
                    return dp[idx][tar] = 1;
                }
            }
        }
        return dp[idx][tar] = 0;
    }
    
}

//-----------------------------------------------------------------------------------------------
// Tab
// Wrong

class Solution{
    
    static Boolean isSubsetSum(int N, int[] arr, int tar) {
        Integer[][] dp = new Integer[N + 1][tar + 1];
        return isSubsetSum_Tab(0, arr, tar, dp) == 1 ? true : false;
    }
    
    public static int isSubsetSum_Tab(int Idx, int[] arr, int Tar, Integer[][] dp) {
        for(int idx = arr.length; idx >= 0; idx--) {
            for(int tar = 0; tar <= Tar; tar++) {
                if(tar == 0 || idx == arr.length) {
                    dp[idx][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }
                
                for(int i = idx; i < arr.length; i++) {
                    if(tar - arr[i] >= 0) {
                        //if(isSubsetSum_Memo(i + 1, arr, tar - arr[i], dp) == 1) {
                          if(dp[i + 1][tar - arr[i]] == 1) {
                            dp[idx][tar] = 1;
                            continue;
                        }
                    }
                }
                dp[idx][tar] = 0;
            }
        }
        return dp[Idx][Tar];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
