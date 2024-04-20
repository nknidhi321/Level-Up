// https://leetcode.com/problems/min-cost-climbing-stairs/

// Dp me kya store hoga ? Humse lekar destination tak ka minimum cost path

// Memoization

class Solution {
    
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        Integer[] dp = new Integer[n + 1];
        climbStairs_Memo(0, n, cost, dp);
        return Math.min(dp[0], dp[1]);
    }
    
    public static int climbStairs_Memo(int stair, int n, int[] cost, Integer[] dp) {
        if(stair == n) return dp[stair] = 0;
        
        if(dp[stair] != null) return dp[stair];
        
        int step1 = 0, step2 = 0;
        if(stair + 1 <= n) step1 = climbStairs_Memo(stair + 1, n, cost, dp);
        if(stair + 2 <= n) step2 = climbStairs_Memo(stair + 2, n, cost, dp);
        return dp[stair] = Math.min(step1, step2) + cost[stair];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution {
    
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        Integer[] dp = new Integer[n + 1];
        climbStairs_Memo(0, n, cost, dp);
        return Math.min(dp[0], dp[1]);
    }
    
    public static void climbStairs_Memo(int STAIR, int n, int[] cost, Integer[] dp) {
        for(int stair = n; stair >= 0; stair--) {
            if(stair == n) {
                dp[stair] = 0;
                continue;
            }

            int step1 = 0, step2 = 0;
            if(stair + 1 <= n) step1 = dp[stair + 1];   // climbStairs_Memo(stair + 1, n, cost, dp);
            if(stair + 2 <= n) step2 = dp[stair + 2];   // climbStairs_Memo(stair + 2, n, cost, dp);
            dp[stair] = Math.min(step1, step2) + cost[stair];
        }
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
