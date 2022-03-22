// https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/ 
// If infinite supply of coins is not mentioned then, consider it as, coins can be used only once
// Not using boolean DP because false is also an answer, Boolean DP could have been used.

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

// Subsequence 
// Tabulation

class Solution {

    public static Boolean isSubsetSum(int n, int arr[], int tar) {
        
        Integer[][] dp = new Integer[n + 1][tar + 1];
        return isSubsetSum_Tab(n, arr, tar, dp) == 1 ? true : false;
    }
    
    public static int isSubsetSum_Tab(int N, int[] arr, int Tar, Integer[][] dp) {
        for(int n = 0; n <= N; n++) {
            for(int tar = 0; tar <= Tar; tar++) {
                if(n == 0 || tar == 0) {
                    dp[n][tar] = tar == 0 ? 1 : 0;
                    continue;
                }
                
                boolean res = false;
                res = res || dp[n - 1][tar] == 1; //isSubsetSum_Memo(n - 1, arr, tar, dp) == 1; // Exclude
                
                if(tar - arr[n - 1] >= 0) {
                    res = res || dp[n - 1][tar - arr[n - 1]] == 1; //isSubsetSum_Memo(n - 1, arr, tar - arr[n - 1], dp) == 1; // Include
                }
                dp[n][tar] = res ? 1 : 0;
            }
        }
        return dp[N][Tar];
    }
    
}

//------------------------------------------------------------------------------------------

// Subsequence 
// back Engineering
  
    public static int targetSum_path(int[] arr, int N, boolean[][] dp, int tar, String psf) {
        if (N == 0 || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[N - 1] >= 0 && dp[N - 1][tar - arr[N - 1]])   // Within boundary raho, and jaha jaha dp me "1" hai, sirf wahi call lgao
            count += targetSum_path(arr, N - 1, dp, tar - arr[N - 1], psf + arr[N - 1] + " ");
        if (dp[N - 1][tar])  // jaha jaha dp me "1" hai, sirf wahi call lgao
            count += targetSum_path(arr, N - 1, dp, tar, psf);

        return count; // Kitne tareeke mile us sum k path ko achieve karne k
    }

    public static void targetSum_backEngg() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10, N = 4;
        boolean[][] dp = new boolean[N + 1][tar + 1];
        System.out.println(targetSum_DP(arr, N, tar, dp));
        System.out.println(targetSum_path(arr, N, dp, tar, ""));
    }

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// P&C method, for loop
// Memoization // Moving from right to left
// "Meaning & understanding" wise intuitive   [Do like this only]

class Solution{
    
    static Boolean isSubsetSum(int N, int[] arr, int tar) {
        Integer[][] dp = new Integer[N + 1][tar + 1];
        return isSubsetSum_Memo(N, arr, tar, dp) == 1 ? true : false;
    }
    
    public static int isSubsetSum_Memo(int n, int[] arr, int tar, Integer[][] dp) {
        if(tar == 0 || n == 0) return dp[n][tar] = (tar == 0) ? 1 : 0;
        
        if(dp[n][tar] != null) return dp[n][tar];
        
        for(int i = n; i > 0; i--) {
            if(tar - arr[i - 1] >= 0) {
                if(isSubsetSum_Memo(i - 1, arr, tar - arr[i - 1], dp) == 1) {
                    return dp[n][tar] = 1;
                }
            }
        }
        return dp[n][tar] = 0;
    }
}

//----------------------------------------------------------------

// Tab 
// Wrong
class Solution{
    
    static Boolean isSubsetSum(int N, int[] arr, int tar) {
        Integer[][] dp = new Integer[N + 1][tar + 1];
        return isSubsetSum_Tab(N, arr, tar, dp) == 1 ? true : false;
    }
    
    public static int isSubsetSum_Tab(int N, int[] arr, int Tar, Integer[][] dp) {
         for(int n = 0; n <= N; n++) {
            for(int tar = 0; tar <= Tar; tar++) {
                if(tar == 0 || n == 0) {
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }
                
                for(int i = n; i > 0; i--) {
                    if(tar - arr[i - 1] >= 0) {
                        //if(isSubsetSum_Memo(i - 1, arr, tar - arr[i - 1], dp) == 1) {
                        if(dp[i - 1][tar - arr[i - 1]] == 1) {
                            dp[n][tar] = 1;
                            continue;
                        }
                    }
                }
                dp[n][tar] = 0;
            }
         }
         return dp[N][Tar];
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// P&C method, for loop
// Memoization // Moving from left to right
// Code & dry run wise intuitive

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
