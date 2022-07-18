// https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/coin-change-permutations-official/ojquestion
// https://leetcode.com/problems/combination-sum-iv/        [This is Permutation question, wrong name of question is given]
// Infinite supply of coin

// Using for loop method so only tar is changing so 1D DP is sufficient // P&C method
// Memoization

```
class Solution {
    
    public int combinationSum4(int[] coins, int tar) {
        int[] dp = new int[tar + 1]; 
        Arrays.fill(dp, -1);
        
        return permutation_Memo(tar, coins, dp);
    }
    
    public static int permutation_Memo(int tar, int[] coins, int[] dp) {
        if(tar == 0) return dp[tar] = 1; 
        
        if(dp[tar] != -1) return dp[tar];
        
        int count = 0;
        for(int ele : coins) {
            if(tar - ele >= 0) {
                count += permutation_Memo(tar - ele, coins, dp);
            } 
        }
        return dp[tar] = count;
    }
}
```

--------------------------------------------------------------------------------------

// Using for loop method so only tar is changing so 1D DP is sufficient // P&C method
// Tabulation

```
class Solution {
    
    public int combinationSum4(int[] coins, int tar) {
        int[] dp = new int[tar + 1]; 
        return permutation_Tab(tar, coins, dp);
    }
    
    public static int permutation_Tab(int Tar, int[] coins, int[] dp) {
        for(int tar = 0; tar <= Tar; tar++) {
            if(tar == 0) {
                dp[tar] = 1;
                continue;
            }

            int count = 0;
            for(int ele : coins) {
                if(tar - ele >= 0) {
                    count += dp[tar - ele]; // permutation_Memo(tar - ele, coins, dp);
                } 
            }
            dp[tar] = count;
        }
        return dp[Tar];
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using pick and not pick method // subsequence method
// Use 2D DP here, because here we need to pick up using index, so 2 variables are changing idx/n and tar 
// Memoization

```
class Solution {
    
    public int combinationSum4(int[] nums, int target) {
        
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];
        for(int[] arr : dp) Arrays.fill(arr, -1);
        
        return pemutationSubsequence_Memo(n, nums, target, dp);
    }
    
    public static int pemutationSubsequence_Memo(int n, int[] nums, int target, int[][] dp) {
        if(target == 0 || n == 0) return dp[n][target] = (target == 0) ? 1 : 0;
        
        if(dp[n][target] != -1) return dp[n][target];
        
        int count = 0;
        if(target - nums[n - 1] >= 0) { // Pick
            count += pemutationSubsequence_Memo(nums.length, nums, target - nums[n - 1], dp);
        }
        count += pemutationSubsequence_Memo(n - 1, nums, target, dp); // Not Pick
        return dp[n][target] = count;
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
