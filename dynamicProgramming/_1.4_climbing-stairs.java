// https://leetcode.com/problems/climbing-stairs/
// Fibonacci optimization possible

// Memoization

class Solution {
    
    public int climbStairs(int n) {
        Integer[] dp = new Integer[n + 1];
        return climbStairs_Memo(0, n, dp);
    }
    
    public static int climbStairs_Memo(int stair, int n, Integer[] dp) {
        if(stair == n) return dp[stair] = 1;
        
        if(dp[stair] != null) return dp[stair];
        
        int count = 0;
        if(stair + 1 <= n) count += climbStairs_Memo(stair + 1, n, dp);
        if(stair + 2 <= n) count += climbStairs_Memo(stair + 2, n, dp);
        return dp[stair] = count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution {
    
    public int climbStairs(int n) {
        Integer[] dp = new Integer[n + 1];
        return climbStairs_Tab(0, n, dp);
    }
    
    public static int climbStairs_Tab(int STAIR, int n, Integer[] dp) {
        for(int stair = n; stair >= 0; stair--) {
            if(stair == n) {
                dp[stair] = 1;
                continue;
            }

            int count = 0;
            if(stair + 1 <= n) count += dp[stair + 1];     // climbStairs_Memo(stair + 1, n, dp);
            if(stair + 2 <= n) count += dp[stair + 2];     // climbStairs_Memo(stair + 2, n, dp);
            dp[stair] = count; 
        }
        return dp[STAIR];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Two pointers, fibonacci 

class Solution {
    
    public int climbStairs(int n) {
        Integer[] dp = new Integer[n + 1];
        return fibo_opti(n);
    }
    
    public static int fibo_opti(int N) {
        int a = 1, b = 1;
        for (int i = 2; i <= N; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
