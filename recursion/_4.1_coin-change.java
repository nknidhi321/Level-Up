//https://leetcode.com/problems/coin-change/
//DP Problem 

/*

	NOTE :
	=====
	1) Use (int)1e9 inplace of Integer.MAX_VALUE, to prevent from overflow, 
		because if we return Integer.MAX_VALUE from recursion and go on adding 1 to it as in this question then it will lead to overflow.
	2) 1e9 simply means (1) * (10^9)
	3) Typecast 1e9 to int since by default is is double => (int)1e9

*/

//--------------------------------------------------------------------------------------------------------------------
//Recursive //TLE

class Solution {
    public int coinChange(int[] coins, int amount) {
        int ans = coinChange_Rec(coins.length, coins, amount);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public int coinChange_Rec(int n, int[] coins, int amount) {
        if(amount == 0 || n == 0) {
            return amount == 0 ? 0 : (int)1e9;
        }
            
        int min1 = (int)1e9; 
        int min2 = (int)1e9;
        
        if(amount - coins[n - 1] >= 0) {
            min1 = coinChange_Rec(n, coins, amount - coins[n - 1]) + 1;
        }
        min2 = coinChange_Rec(n - 1, coins, amount); 
     
        return Math.min(min1, min2);
    }
    
}

//-----------------------------------------------------------------------------------------------------------------------
//Memoization

class Solution {
    public int coinChange(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length + 1][amount + 1];
    
        int ans = coinChange_Memo(coins, coins.length, amount, dp);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public static int coinChange_Memo(int[] coins, int n, int amount, Integer[][] dp) {
        if(amount == 0 || n == 0) {
            if(amount == 0) {
                return dp[n][amount] = 0;
            }
            return dp[n][amount] = (int)1e9;
        }
    
        if(dp[n][amount] != null) return dp[n][amount];
        
        int min1 = (int)1e9;
        int min2 = (int)1e9;
        if(amount - coins[n - 1] >= 0){
           min1 = coinChange_Memo(coins, n, amount - coins[n - 1], dp) + 1;
        }
        min2 = coinChange_Memo(coins, n - 1, amount, dp);
        return dp[n][amount] = Math.min(min1, min2);
    }
}

//--------------------------------------------------------------------------------------------------------------------------
//Tabulation

class Solution {
    public int coinChange(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length + 1][amount + 1];
    
        int ans = coinChange_Tab(coins, coins.length, amount, dp);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public static int coinChange_Tab(int[] coins, int N, int AMOUNT, Integer[][] dp) {
        for(int n = 0; n <= coins.length; n++) {
            for(int amount = 0; amount <= AMOUNT; amount++) {
                if(amount == 0 || n == 0) {
                    if(amount == 0){
                        dp[n][amount] = 0;
                        continue;
                    }
                    dp[n][amount] = (int)1e9;
                    continue;
                }

                int min1 = (int)1e9;
                int min2 = (int)1e9;
                if(amount - coins[n - 1] >= 0){
                   min1 = dp[n][amount - coins[n - 1]] + 1; //coinChange_Memo(coins, n, amount - coins[n - 1], dp) + 1;
                }
                min2 = dp[n - 1][amount]; //coinChange_Memo(coins, n - 1, amount, dp);
                dp[n][amount] = Math.min(min1, min2);
            }
        }
        return dp[N][AMOUNT];
    }
}
