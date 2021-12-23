// https://leetcode.com/problems/house-robber/

// Dp in kya store hoga ? 
// Jab n number of houses hai toh maximum amount of money un n houses me se kya h wo dp[n] pe store hoga 

// Memoization

class Solution {
    
    public int rob(int[] nums) {
        int n = nums.length;
        Integer[] dp = new Integer[n + 1];
        return rob_Memo(n, nums, dp);
    }
    
    public static int rob_Memo(int n, int[] nums, Integer[] dp) {
      
        // Jab house = 0 hai toh max 0 steal kar saktey ho
        // Jab house = 1 hai toh max usi ek house ko steal kar sakey ho
        if(n == 0 || n == 1) return dp[n] = (n == 0 ? 0 : nums[n - 1]);  
        
        if(dp[n] != null) return dp[n];
        
        // Max of adjacent and currHouse se steal karne ka chance tvi milega jab house >= 2 honge
        int adjacentMax = 0, currMax = 0;
        adjacentMax = rob_Memo(n - 1, nums, dp);  // Not taking curr element
        currMax = rob_Memo(n - 2, nums, dp) + nums[n - 1];  // Taking curr element
        
        // Now, take the max of the above two choices
        return dp[n] = Math.max(adjacentMax, currMax);
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX// Tabulation

class Solution {
    
    public int rob(int[] nums) {
        int n = nums.length;
        Integer[] dp = new Integer[n + 1];
        return rob_Tab(n, nums, dp);
    }
    
    public static int rob_Tab(int N, int[] nums, Integer[] dp) {     
        for(int n = 0; n <= N; n++) {
            if(n == 0 || n == 1) {
                dp[n] = (n == 0 ? 0 : nums[n - 1]);
                continue;
            }

            int adjacentMax = 0, currMax = 0;
            adjacentMax = dp[n - 1]; // rob_Memo(n - 1, nums, dp)
            currMax = dp[n - 2] + nums[n - 1]; // rob_Memo(n - 2, nums, dp)

            dp[n] = Math.max(adjacentMax, currMax);
        }
        return dp[N];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Optimization

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
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
