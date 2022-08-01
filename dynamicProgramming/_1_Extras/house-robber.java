// https://leetcode.com/problems/house-robber/

/*
    Dp in kya store hoga ? 
    Jab n number of houses hai toh maximum amount of money un n houses me se kya h wo dp[n] pe store hoga 
*/

// Memoization
```
class Solution {
    
    public int rob(int[] nums) {
        int n = nums.length;
        Integer[] dp = new Integer[n + 1];
        return rob_Memo(n, nums, dp);
    }
    
    public static int rob_Memo(int n, int[] nums, Integer[] dp) {
        // Jab house = 0 hai toh max 0 steal kar saktey ho
        if(n == 0) return dp[0] = 0;  
        
        if(dp[n] != null) return dp[n];
        
        // Max of adjacent and currHouse se steal karne ka chance tvi milega jab house >= 2 honge
        int adjacentMax = 0, adjacentsAdjacentMax = 0;
        
        adjacentMax = rob_Memo(n - 1, nums, dp);   // Not taking curr element
        if(n - 2 >= 0) adjacentsAdjacentMax = rob_Memo(n - 2, nums, dp);   // Taking curr element
        
		// Now, take the max of the above two choices
        return dp[n] = Math.max(adjacentMax, adjacentsAdjacentMax + nums[n - 1]);
    }
    
}
```
-------------------------------------------------------------
// Tabulation
```
class Solution {
    
    public int rob(int[] nums) {
        int n = nums.length;
        Integer[] dp = new Integer[n + 1];
        return rob_Tab(n, nums, dp);
    }
    
    public static int rob_Tab(int N, int[] nums, Integer[] dp) {     
        for(int n = 0; n <= N; n++) {
            if(n == 0) {dp[0] = 0; continue;}

            int adjacentMax = 0, adjacentsAdjacentMax = 0;
            adjacentMax = dp[n - 1]; // rob_Memo(n - 1, nums, dp)
            if(n - 2 >= 0) adjacentsAdjacentMax = dp[n - 2]; // rob_Memo(n - 2, nums, dp)

            dp[n] = Math.max(adjacentMax, adjacentsAdjacentMax  + nums[n - 1]);
        }
        return dp[N];
    }
}
```
-------------------------------------------------------------
// Optimization 1 
// Using Include exclude
```
class Solution {
    
    public int rob(int[] arr) {
        int n = arr.length;
        int incSum = arr[0];
        int excSum = 0;
        
        for(int i = 1; i < n; i++) {
            int newincSum = excSum + arr[i];
            int newexcSum = Math.max(incSum, excSum);
           
            incSum = newincSum;
            excSum = newexcSum;
        }
        return Math.max(incSum, excSum);
    }
    
}
```
-------------------------------------------------------------
// Optimization 2
// Fibo
```
class Solution {
    
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        return rob_opti(n, nums);
    }
    
    public static int rob_opti(int N, int[] nums) {
        int a = 0, b = 0;
        for(int n = 1; n <= N; n++) {
            int max = Math.max(b, a + nums[n - 1]);
            a = b;
            b = max;
        }
        return b;
    }
}
```
-------------------------------------------------------------
// Fibo optimization cannot be done if u use Pair class, and make calls to n - 1
// Instead make calls to n - 1, and n - 2, for curr and adj max, like above codes.
```
class Solution {
    
    public static class Pair {
        int withCurrRobbing;
        int withoutCurrRobbing;
        
        public Pair(int withCurrRobbing, int withoutCurrRobbing) {
            this.withCurrRobbing = withCurrRobbing;
            this.withoutCurrRobbing = withoutCurrRobbing;
        }
    }
    
    public int rob(int[] arr) {
        int n = arr.length;
        Pair[] dp = new Pair[n + 1];
        Pair p = maxAmount(n, arr, dp);
        return Math.max(p.withCurrRobbing, p.withoutCurrRobbing);
    }
    
    public Pair maxAmount(int n, int[] arr, Pair[] dp) {
        if(n == 0) return dp[0] = new Pair(0, 0);
        
        if(dp[n] != null) return dp[n];
        
        Pair p = maxAmount(n - 1, arr, dp);
        int withoutPrevRobbing = p.withoutCurrRobbing;
        int withPrevRobbing = p.withCurrRobbing;
        
        int withCurrRobbing = withoutPrevRobbing + arr[n - 1];
        int withoutCurrRobbing = Math.max(withoutPrevRobbing, withPrevRobbing);
        return dp[n] = new Pair(withCurrRobbing, withoutCurrRobbing);
    }
    
}
```
-------------------------------------------------------------
