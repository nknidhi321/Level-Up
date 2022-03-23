// https://leetcode.com/problems/partition-equal-subset-sum/
// Aditya Verma

// Memoization

```
class Solution {
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int val : nums) sum += val;
        
        // Agar sbka summation even nahi hai
        // toh duniya ki koi taqat 2 equal partition set nahi bna sakti, kuki integer numbers hai
        if(sum % 2 != 0) return false;  
        
        // Aur agar summation even hai toh sum/2 target dhoondh lo poore array me se
        // Agar ek set of numbers ka target sum/2 hai toh ultimately dusra v sum/2 target ka set he hoga 
        else return isSubsetSum(nums.length, nums, sum/2); 
    }
    
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
```

----------------------------------------------------------------------------------------------------------

// Tabulation

```
class Solution {
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int val : nums) sum += val;
        
        // Agar sbka summation even nahi hai
        // toh duniya ki koi taqat 2 equal partition set nahi bna sakti, kuki integer numbers hai
        if(sum % 2 != 0) return false;  
        
        // Aur agar summation even hai toh sum/2 target dhoondh lo poore array me se
        // Agar ek set of numbers ka target sum/2 hai toh ultimately dusra v sum/2 target ka set he hoga 
        else return isSubsetSum(nums.length, nums, sum/2); 
    }
    
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
```
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
